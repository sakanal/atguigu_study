package com.sakanal.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@MapperScan({"com.sakanal.springcloud.dao"})
public class SeateStorageMainApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeateStorageMainApp2002.class,args);
    }
}
