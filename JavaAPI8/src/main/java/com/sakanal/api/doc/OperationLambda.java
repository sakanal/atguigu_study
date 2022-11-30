package com.sakanal.api.doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.core.bulk.DeleteOperation;
import com.sakanal.api.bean.User;
import com.sakanal.api.utils.ESClientUtils;

import java.util.ArrayList;
import java.util.List;

public class OperationLambda {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchClient client = ESClientUtils.getClient();
        String index="user";

        //文档创建
        User user = new User(1001L, "张三", 24);
        CreateResponse createResponse = client.create(
                req -> req.index(index)
                        .id(String.valueOf(user.getId()))
                        .document(user));
        System.out.println(createResponse);

        // 批量文档创建

        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userList.add(new User(2000L+i,"李四"+i,18));
        }
        BulkResponse bulkCreateResponse = client.bulk(
                req -> {
                    userList.forEach(
                            userInfo -> {
                                req.operations(
                                        builder -> builder.create(
                                                operation -> operation.index(index).id(String.valueOf(userInfo.getId())).document(userInfo)
                                        )
                                );
                            }
                    );
                    return req;
                }
        );
        System.out.println(bulkCreateResponse);

        //删除文档
        DeleteResponse deleteResponse = client.delete(
                req -> req.index(index)
                        .id(String.valueOf(1001)));
        System.out.println(deleteResponse);

        BulkResponse bulkDeleteResponse = client.bulk(
                req -> {
                    userList.forEach(
                            userInfo -> {
                                req.operations(
                                        builder -> builder.delete(
                                                operation -> operation.index(index).id(String.valueOf(userInfo.getId()))
                                        )
                                );
                            }
                    );
                    return req;
                }
        );
        System.out.println(bulkDeleteResponse);

        ESClientUtils.close();
    }
}
