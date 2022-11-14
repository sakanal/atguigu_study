package com.sakanal.mvc.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String toIndex(){
        System.out.println("Hello Controller");
        return "index";
    }
    @RequestMapping("/hello")
    public String toHello(){
        return "hello";
    }
}
