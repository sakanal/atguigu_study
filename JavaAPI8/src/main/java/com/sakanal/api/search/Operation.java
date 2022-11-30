package com.sakanal.api.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.sakanal.api.utils.ESClientUtils;

public class Operation {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchClient client = ESClientUtils.getClient();

        MatchQuery matchQuery = new MatchQuery.Builder().field("age").query(24).build();
        Query query = new Query.Builder().match(matchQuery).build();
        SearchRequest searchRequest = new SearchRequest.Builder().query(query).build();
        SearchResponse<Object> search = client.search(searchRequest, Object.class);

        System.out.println(search);

        ESClientUtils.close();
    }
}
