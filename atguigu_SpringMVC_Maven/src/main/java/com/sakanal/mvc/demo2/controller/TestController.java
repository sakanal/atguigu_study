package com.sakanal.mvc.demo2.controller;

import com.sakanal.mvc.demo2.dao.UserMapper;
import com.sakanal.mvc.demo2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
    @RequestMapping("/param")
    public String toParam(){
        return "param";
    }

//    public ModelAndView toGetUser(ModelAndView modelAndView){
//        List<User> userAll = userMapper.getUserAll();
//        modelAndView.addObject("userAll",userAll);
//        modelAndView.setViewName("info");
//        return modelAndView;
//    }
    @RequestMapping("/testGetUser")
    public String toGetUser(Map<String,Object> map){
        List<User> userAll = userMapper.getUserAll();
//        User user = userAll.get(2);
//        Integer id = user.getId();
//        String name = user.getName();
//        String password = user.getPassword();
//        String gender = user.getGender();
//        String phone = user.getPhone();
//        String address = user.getAddress();
//
//        map.put("id",id);
//        map.put("name",name);
//        map.put("password",password);
//        map.put("gender",gender);
//        map.put("phone",phone);
//        map.put("address",address);
        map.put("userAll",userAll);

        return "info";
    }
}
