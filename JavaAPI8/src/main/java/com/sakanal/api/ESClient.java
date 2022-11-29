package com.sakanal.api;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class ESClient {
    public static void main(String[] args) throws Exception {
        initESConnection();

    }
    private static void initESConnection()throws Exception{
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic","elastic"));

        Path path = Paths.get("JavaAPI8/certs/es-api-ca.crt");
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        Certificate trustedCa;

        try (InputStream inputStream = Files.newInputStream(path)){
            trustedCa = factory.generateCertificate(inputStream);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");

        trustStore.load(null,null);
        trustStore.setCertificateEntry("ca",trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStore, null);
        final SSLContext sslContext = sslContextBuilder.build();

        RestClientBuilder builder = RestClient.builder(new HttpHost("CentOS7", 9200, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setSSLContext(sslContext)
                                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        RestClient restClient = builder.build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        //同步客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);
        //异步客户端
        //ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);

        transport.close();

    }
}
