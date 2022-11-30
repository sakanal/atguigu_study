package com.sakanal.api;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.sakanal.api.utils.ESClientUtils;

public class asyncClient {
    public static void main(String[] args) throws Exception {
        ElasticsearchAsyncClient asyncClient = ESClientUtils.getAsyncClient();

        asyncClient.search(SearchRequest.of(
                searchRequest->searchRequest.query(Query.of(
                        query->query.fuzzy(FuzzyQuery.of(
                                fuzzyQuery->fuzzyQuery.field("name").value("张")
                        ))
                ))
        ),Object.class)
                .thenApply(ResponseBody::hits)
                .whenComplete((resp,error)->{
                    System.out.println(resp.hits());
                    System.out.println(error);
                });

        System.out.println("主程序...");
    }
}
