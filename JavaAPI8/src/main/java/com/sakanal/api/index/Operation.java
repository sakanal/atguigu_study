package com.sakanal.api.index;

import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.security.User;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.sakanal.api.utils.ESClientUtils;

import java.io.IOException;

public class Operation {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchIndicesClient client = ESClientUtils.getClient().indices();

        String index = "user";

        //判断索引是否存在
        ExistsRequest existsRequest = new ExistsRequest.Builder().index(index).build();
        BooleanResponse booleanResponse = client.exists(existsRequest);

        if (!booleanResponse.value()){
            //创建索引
            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index(index).build();
            CreateIndexResponse createIndexResponse = client.create(createIndexRequest);
            System.out.println("创建索引的响应对象-->"+createIndexResponse);
        }else {
            System.out.println(index+"索引已存在");
        }

        //搜索索引
        GetIndexRequest getIndexRequest = new GetIndexRequest.Builder().index(index).build();
        GetIndexResponse getIndexResponse = client.get(getIndexRequest);
        IndexState indexState = getIndexResponse.get(index);
        System.out.println(indexState);
        System.out.println(getIndexResponse);

        //删除索引
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest.Builder().index(index).build();
        DeleteIndexResponse deleteIndexResponse = client.delete(deleteIndexRequest);
        System.out.println("索引删除结果："+deleteIndexResponse.acknowledged());

        ESClientUtils.close();
    }
}
