package com.sakanal.mvc.demo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/test_view")
    public String toTestView(){
        return "test_view";
    }
}
