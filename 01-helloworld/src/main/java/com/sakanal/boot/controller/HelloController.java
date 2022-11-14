package com.sakanal.boot.controller;

import com.sakanal.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/hello")
    @ResponseBody
    public String toHello(@RequestParam(value = "name",required = false)String name){
        log.info("请求进来了");
        return "Hello,Spring Boot 2!"+name;
    }

    @RequestMapping("/mycar")
    @ResponseBody
    public Car getCar(){
        return car;
    }
}
