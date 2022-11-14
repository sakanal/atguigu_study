package com.sakanal.mvc.demo5.controller;

import com.sakanal.mvc.demo5.pojo.User;
import com.sakanal.mvc.demo5.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class HttpController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody = " + requestBody);
        return "success";
    }
    @PostMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        //当前RequestEntity表示整个请求报文的信息
        System.out.println("请求头(RequestHeaders)"+requestEntity.getHeaders());
        System.out.println("请求体(RequestBody)"+requestEntity.getBody());
        return "success";
    }
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("hello,Response");
        response.getWriter().println("中文测试");
    }
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";
    }
    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        List<User> users = userService.getUsers();
        return users.get(0);
    }
    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username,String password){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "hello,axios";
    }
}
