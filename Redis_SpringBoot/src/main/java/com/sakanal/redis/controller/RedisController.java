package com.sakanal.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {
    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/testRedis")
    public String testRedis(){
        redisTemplate.opsForValue().set("name","张三");
        Object name = redisTemplate.opsForValue().get("name");
        return (String) name;
    }
}
