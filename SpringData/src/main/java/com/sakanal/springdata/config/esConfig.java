package com.sakanal.springdata.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import nl.altindag.ssl.SSLFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Slf4j
@Configuration
public class esConfig{


//    @Bean
    public RestClient restClient(){
        SSLFactory sslFactory = SSLFactory.builder()
                .withUnsafeTrustMaterial()
                .withUnsafeHostnameVerifier()
                .build();

        RestClient restClient = RestClient
                .builder(new HttpHost("192.168.38.131", 9200, "https"))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setSSLContext(sslFactory.getSslContext())
                                .setSSLHostnameVerifier(sslFactory.getHostnameVerifier())
                ).build();
        log.info("created restClient success");
        return restClient;
    }

}
