package com.sakanal.boot.controller;

import com.sakanal.boot.entity.User;
import com.sakanal.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<User> users = userService.getAllUsers();
        modelAndView.addObject("users",users);
        return modelAndView;
    }
}
