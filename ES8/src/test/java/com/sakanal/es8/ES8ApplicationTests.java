package com.sakanal.es8;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class ES8ApplicationTests {

    @Resource
    private ElasticsearchClient elasticsearchClient;

    @Test
    public void testIndex() throws  IOException{
        GetIndexResponse getIndexResponse = elasticsearchClient.indices().get(GetIndexRequest.of(
                getIndexRequest -> getIndexRequest.index("user")
        ));
        System.out.println(getIndexResponse);
    }

    @Test
    public void testExistsIndex() throws IOException{
        BooleanResponse booleanResponse = elasticsearchClient.indices().exists(ExistsRequest.of(
                existsRequest -> existsRequest.index("user")
        ));
        System.out.println(booleanResponse);
    }
    @Test
    public void testAddIndex() throws  IOException{
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(c -> c.index("test"));
        System.out.println(createIndexResponse);
    }

}
