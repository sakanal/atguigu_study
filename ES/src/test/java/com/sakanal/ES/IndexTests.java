package com.sakanal.ES;

import com.sakanal.ES.bean.Product;
import com.sakanal.ES.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class IndexTests {
    //注入 ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //创建索引并增加映射配置
    @Test
    public void createIndex(){
        //创建索引，系统初始化会自动创建索引
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex(){
        boolean flag = elasticsearchRestTemplate.indexOps(Product.class).delete();
        System.out.println("删除索引 = " + flag);
    }

}
