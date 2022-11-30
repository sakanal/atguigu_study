package com.sakanal.api.doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.core.bulk.DeleteOperation;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.sakanal.api.bean.User;
import com.sakanal.api.utils.ESClientUtils;

import java.util.ArrayList;
import java.util.List;

public class Operation {
    public static void main(String[] args) throws Exception {
        //索引客户端
        ElasticsearchClient client = ESClientUtils.getClient();
        String index="user";

        //文档创建
        User user = new User(1001L, "张三", 24);
        CreateRequest<User> userCreateRequest = new CreateRequest.Builder<User>().index(index)
                .id(String.valueOf(user.getId()))
                .document(user)
                .build();
        CreateResponse createResponse = client.create(userCreateRequest);
        System.out.println(createResponse);

        // 批量文档创建
        List<BulkOperation> operationList = new ArrayList<BulkOperation>();
        for (int i = 1 ; i <= 5; i++) {
            User userInfo = new User(2000L+i,"李四"+i,18);
            CreateOperation<User> userCreateOperation = new CreateOperation.Builder<User>()
                    .id(String.valueOf(userInfo.getId()))
                    .document(userInfo)
                    .build();
            BulkOperation bulkOperation = new BulkOperation.Builder().create(userCreateOperation).build();
            operationList.add(bulkOperation);
        }
        BulkRequest bulkCreateRequest = new BulkRequest.Builder().index(index).operations(operationList).build();
        BulkResponse bulkCreateResponse = client.bulk(bulkCreateRequest);
        System.out.println(bulkCreateResponse);

        //删除文档
        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index(index)
                .id(String.valueOf(1001L))
                .build();
        DeleteResponse deleteResponse = client.delete(deleteRequest);
        System.out.println(deleteResponse);

        // 批量文档删除
        operationList = new ArrayList<BulkOperation>();
        for (int i = 1; i <= 5; i++) {
            User userInfo = new User(2000L+i,"李四"+i,18);
            DeleteOperation deleteOperation = new DeleteOperation.Builder().id(String.valueOf(2000L + i)).build();
            BulkOperation bulkOperation = new BulkOperation.Builder().delete(deleteOperation).build();
            operationList.add(bulkOperation);
        }
        BulkRequest bulkDeleteRequest = new BulkRequest.Builder().index(index).operations(operationList).build();
        BulkResponse bulkDeleteResponse = client.bulk(bulkDeleteRequest);
        System.out.println(bulkDeleteResponse);

        ESClientUtils.close();
    }
}
