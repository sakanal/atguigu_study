package com.sakanal.mvc.demo4.controller;

import com.sakanal.mvc.demo4.pojo.User;
import com.sakanal.mvc.demo4.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //添加
    @PostMapping("/user")
    public ModelAndView insertUser(User user){
        userService.insertUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }
    //修改
    @PutMapping("/user")
    public ModelAndView updateUser(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        userService.updateUser(user);
        return modelAndView;
    }
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id")String id, Model model){
        User user = new User();
        user.setId(Integer.valueOf(id));
        List<User> users = userService.getUserByLike(user);
        model.addAttribute("user",users.get(0));
        return "/CRUD/update";
    }
    //删除
    @DeleteMapping("/user/{id}")
    public ModelAndView deleteUser(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        userService.deleteUser(user);
        return modelAndView;
    }
    //查找所有
    @GetMapping("/user")
    public ModelAndView selectUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getUsers();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    //条件模糊查询
    @RequestMapping("/userByLike")
    public ModelAndView selectUserByLike(User user){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userByLike = userService.getUserByLike(user);
        modelAndView.addObject("users",userByLike);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
