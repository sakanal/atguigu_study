package com.sakanal.api.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.sakanal.api.utils.ESClientUtils;

public class OperationLambda {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchClient client = ESClientUtils.getClient();

        SearchResponse<Object> searchResponse = client.search(SearchRequest.of(
                searchRequest -> searchRequest.query(Query.of(
                        query-> query.fuzzy(FuzzyQuery.of(
                                fuzzyQuery->fuzzyQuery.field("name").value("李")
                        ))
                ))
        ), Object.class);
        System.out.println(searchResponse);


        ESClientUtils.close();
    }
}
