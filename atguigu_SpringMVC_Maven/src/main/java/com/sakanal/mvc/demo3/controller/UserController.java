package com.sakanal.mvc.demo3.controller;

import com.sakanal.mvc.demo3.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    /**
     *  使用RESTFul模拟用户资源的增删改查
     *  /user       GET         查询所有用户信息
     *  /user/1     GET         根据id查询用户信息
     *  /user       POST        添加用户信息
     *  /user/1     DELETE     根据id删除用户信息
     *  /user       PUT         修改用户信息
     */
    @GetMapping("/user")
    public ModelAndView testGetMethod1(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testMethod","GET：查询所有用户");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @GetMapping("/user/{id}")
    public ModelAndView testGetMethod2(@PathVariable("id")String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testMethod","GET：根据id查询用户");
        modelAndView.addObject("value",id);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @PostMapping("/user")
    public ModelAndView testPostMethod(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testMethod","POST：添加用户");
        modelAndView.addObject("value",user);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @DeleteMapping("/user/{id}")
    public ModelAndView testDeleteMethod(@PathVariable("id")String id,User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testMethod","DELETE：根据id删除用户");
        modelAndView.addObject("value",user);
        modelAndView.setViewName("success");
        System.out.println(id);
        System.out.println(user);
        return modelAndView;
    }
    @PutMapping("/user")
    public ModelAndView testPutMethod(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testMethod","PUT：修改用户");
        modelAndView.addObject("value",user);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
