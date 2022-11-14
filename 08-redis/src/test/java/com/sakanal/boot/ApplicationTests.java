package com.sakanal.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSet() {
        //存数据
        redisTemplate.boundValueOps("name").set("zhansan");
    }
    @Test
    void testGet(){
        //获取数据
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println("name = " + name);
    }

}
