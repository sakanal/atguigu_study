package com.sakanal.api.utils;

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
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class ESClientUtils {
    private ESClientUtils(){}
    private static ElasticsearchClient client;
    private static ElasticsearchAsyncClient asyncClient;
    private static RestClientTransport transport;

    public static ElasticsearchClient getClient() throws Exception {
        if (client==null){
            initESConnection(false);
        }
        return client;
    }
    public static ElasticsearchAsyncClient getAsyncClient() throws Exception {
        if (asyncClient==null){
            initESConnection(true);
        }
        return asyncClient;
    }
    public static void close() throws IOException {
        transport.close();
    }

    private static void initESConnection(boolean isAsync)throws Exception{
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic","L1OErkHC0h8qeUISsH4w"));

        Path path = Paths.get("JavaAPI8/certs/http_ca.crt");
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

        RestClientBuilder builder = RestClient.builder(new HttpHost("192.168.38.131", 9200, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setSSLContext(sslContext)
                                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        RestClient restClient = builder.build();
        transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        if (!isAsync){
            //同步客户端
            client = new ElasticsearchClient(transport);
        }else {
            //异步客户端
            asyncClient = new ElasticsearchAsyncClient(transport);
        }

    }
}
