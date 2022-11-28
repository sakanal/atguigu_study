package com.sakanal.ES.doc;

import cn.hutool.json.JSONUtil;
import com.sakanal.ES.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Get {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        GetRequest request = new GetRequest().index("user").id("1001");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());
        Map<String, Object> source = response.getSource();
        Set<String> keySet = source.keySet();
        for (String key : keySet) {
            System.out.println(key+":"+source.get(key));
        }

        client.close();
    }
}
