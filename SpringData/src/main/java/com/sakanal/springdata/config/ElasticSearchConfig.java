package com.sakanal.springdata.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Slf4j
//@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String uris;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        ElasticsearchTransport transport = getElasticsearchTransport();
        return new ElasticsearchClient(transport);
    }

    public ElasticsearchTransport getElasticsearchTransport() {
        // 账号密码的配置
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        log.info("账号密码设置成功");

        // 自签证书的设置，并且还包含了账号密码
        RestClientBuilder.HttpClientConfigCallback callback = httpAsyncClientBuilder -> httpAsyncClientBuilder
                .setSSLContext(buildSSLContext())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setDefaultCredentialsProvider(credentialsProvider);

        log.info("HttpClientConfigCallback 创建成功");

        // 用builder创建RestClient对象
        RestClient client = RestClient
                .builder(getHttpHost())
                .setHttpClientConfigCallback(callback)
                .build();

        log.info("restClient 创建成功");

        return new RestClientTransport(client, new JacksonJsonpMapper());
    }

    /**
     * 解析配置的字符串，转为HttpHost对象数组
     * @return
     */
    private HttpHost[] getHttpHost() {
        if (!StringUtils.hasLength(uris)) {
            throw new RuntimeException("invalid elasticsearch configuration");
        }

        String[] hostArray = uris.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        for (int i = 0; i < hostArray.length; i++) {
            String url = hostArray[i].split("://")[1];
            String[] hostAndPort = url.split(":");
            httpHost = new HttpHost(hostAndPort[0], Integer.parseInt(hostAndPort[1]), "https");
            httpHosts[i] = httpHost;
        }

        log.info("httpHost 获取成功");
        return httpHosts;
    }

    private static SSLContext buildSSLContext() {
        ClassPathResource resource = new ClassPathResource("http_ca.crt");
        log.info("crt文件读取成功");
        SSLContext sslContext = null;
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Certificate trustedCa;
            try (InputStream is = resource.getInputStream()) {
                trustedCa = factory.generateCertificate(is);
            }
            KeyStore trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load(null, null);
            trustStore.setCertificateEntry("ca", trustedCa);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                    .loadTrustMaterial(trustStore, null);
            sslContext = sslContextBuilder.build();
        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException |
                KeyManagementException e) {
            log.error("ES连接认证失败", e);
        }

        return sslContext;
    }

}
