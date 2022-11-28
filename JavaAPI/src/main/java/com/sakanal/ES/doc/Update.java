package com.sakanal.ES.doc;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Update {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        UpdateRequest request = new UpdateRequest().index("user").id("1001")
                .doc(XContentType.JSON, "sex", "女");

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());

        client.close();
    }
}
