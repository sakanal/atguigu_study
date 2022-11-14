package com.sakanal.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.sakanal.springcloud.dao"})
public class MyBatisConfig {
}

