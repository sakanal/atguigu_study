package com.sakanal.mvc.demo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @RequestMapping("/testThymeleafView")
    public ModelAndView testThymeleafView(){
        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("testRequest","测试ThymeleafView");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testForward")
    public ModelAndView testForward(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("testRequest","测试forward前缀");
        modelAndView.setViewName("forward:/testThymeleafView");
        return modelAndView;
    }

    @RequestMapping("/testRedirect")
    public ModelAndView testRedirect(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testRequest","测试redirect前缀");
        modelAndView.setViewName("redirect:/testThymeleafView");
        return modelAndView;
    }
}
