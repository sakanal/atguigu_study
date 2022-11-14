package com.sakanal.boot.config;

import com.sakanal.boot.bean.User;
import com.sakanal.boot.condition.ConditionOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class UserConfig {

    @Bean
//    @Conditional(ClassCondition.class)
    @ConditionOnClass({"redis.clients.jedis.Jedis","com.alibaba.fastjson.JSON"})
    public User user(){
        return new User();
    }

    @Bean
    //配置文件中有key=myUser，value=sakanal时才会加载这个bean
    @ConditionalOnProperty(name = "myUser",havingValue = "sakanal")
    public User myUser(){
        return new User();
    }
}
