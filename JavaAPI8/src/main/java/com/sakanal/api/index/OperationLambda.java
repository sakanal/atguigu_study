package com.sakanal.api.index;

import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.sakanal.api.utils.ESClientUtils;

public class OperationLambda {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchIndicesClient client = ESClientUtils.getClient().indices();
        String index = "user";

        //判断索引是否存在
        BooleanResponse booleanResponse = client.exists(req -> req.index(index));

        if (!booleanResponse.value()){
            //创建索引
            CreateIndexResponse createIndexResponse = client.create(req -> req.index(index));
            System.out.println("创建索引的响应对象-->"+createIndexResponse);
        }else {
            System.out.println(index+"索引已存在");
        }

        //搜索索引
        GetIndexResponse getIndexResponse = client.get(req -> req.index(index));
        IndexState indexState = getIndexResponse.get(index);
        System.out.println(indexState);
        System.out.println(getIndexResponse);

        //删除索引
        DeleteIndexResponse deleteIndexResponse = client.delete(req -> req.index(index));
        System.out.println("索引删除结果："+deleteIndexResponse.acknowledged());

        ESClientUtils.close();
    }
}
