package com.sakanal.ES.doc;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Delete {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        DeleteRequest request = new DeleteRequest().index("user").id("1001");

        DeleteResponse response = client.delete(request,RequestOptions.DEFAULT);

        System.out.println(response.status());

        client.close();
    }
}
