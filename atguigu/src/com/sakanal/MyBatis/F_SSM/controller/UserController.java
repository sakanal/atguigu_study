package com.sakanal.MyBatis.F_SSM.controller;

import com.sakanal.MyBatis.F_SSM.pojo.User;
import com.sakanal.MyBatis.F_SSM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired(required = false)
    UserService userService;

//    @RequestMapping("/getUsers")
//    public ModelAndView getUsers(ModelAndView modelAndView){
//        List<User> users = userService.getUsers();
//        modelAndView.addObject(users);
//        modelAndView.setViewName("success");
//        return modelAndView;
//    }
    @RequestMapping("/getUsers")
    public String getUsers(Map<String,Object> map){
        List<User> users = userService.getUsers();
        map.put("users", users);
        return "success";
    }
}
