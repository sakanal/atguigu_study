package com.sakanal.ES.queryPlus;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

//trems适用于keywords类型数据
public class AggregationTerms {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        SearchRequest searchRequest = new SearchRequest().indices("user");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        searchSourceBuilder.aggregation(aggregationBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);

        String jsonString = new ObjectMapper().writeValueAsString(response.getAggregations());
        System.out.println(jsonString);
        String jsonStr = JSONUtil.toJsonStr(response.getAggregations());
        System.out.println(jsonStr);

        List<Aggregation> aggregationList = response.getAggregations().asList();
        for (Aggregation aggregation : aggregationList) {
            System.out.println(aggregation.getMetadata());
            System.out.println(aggregation.getName());
            System.out.println(aggregation.getType());
        }

        client.close();
    }
}
