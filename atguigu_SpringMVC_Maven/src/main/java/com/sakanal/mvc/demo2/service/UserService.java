package com.sakanal.mvc.demo2.service;

import com.sakanal.mvc.demo2.dao.UserMapper;
import com.sakanal.mvc.demo2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(User user){
        return userMapper.insertUser(user);
    }

    public List<User> getUserAll(){
        return userMapper.getUserAll();
    }
}
