package com.sakanal.ES.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class Create {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        CreateIndexRequest indexRequest = new CreateIndexRequest("user");

        CreateIndexResponse createIndexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("创建索引："+acknowledged);

        String index = createIndexResponse.index();
        System.out.println(index);

        client.close();
    }
}
