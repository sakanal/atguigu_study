package com.sakanal.mybatisPlus.controller;

import com.sakanal.mybatisPlus.entity.User;
import com.sakanal.mybatisPlus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> getAll(){
        List<User> all = userService.getAll();
        for (User user:all){
            log.info(user+"");
        }
        return all;
    }
}
