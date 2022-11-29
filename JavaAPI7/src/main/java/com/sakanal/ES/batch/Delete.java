package com.sakanal.ES.batch;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

public class Delete {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 10; i++) {
            request.add(new DeleteRequest().index("user").id("100"+(i+1)));
        }
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(responses.getTook());
        System.out.println(Arrays.toString(responses.getItems()));

        client.close();
    }
}
