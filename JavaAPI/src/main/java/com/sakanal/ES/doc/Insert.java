package com.sakanal.ES.doc;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Insert {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        User user = new User();
        user.setId("1001");
        user.setName("张三");
        user.setSex("男");
        user.setAge(18);
        String userJson = JSONUtil.toJsonStr(user);

        IndexRequest indexRequest = new IndexRequest().index("user").id(user.getId());
        indexRequest.source(userJson,XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(indexResponse.getResult());

        client.close();
    }
}
