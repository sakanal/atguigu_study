package com.sakanal.ES.batch;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

public class Insert {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId("100"+(i+1));
            user.setName("张三");
            user.setSex("男");
            user.setAge(18);
            String userJson = JSONUtil.toJsonStr(user);
            request.add(new IndexRequest().index("user").id(user.getId()).source(userJson,XContentType.JSON));
        }
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(responses.getTook());
        System.out.println(Arrays.toString(responses.getItems()));

        client.close();
    }
}
