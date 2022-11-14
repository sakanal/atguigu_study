package com.sakanal.MyBatis.F_SSM.service;

import com.sakanal.MyBatis.F_SSM.dao.UserMapper;
import com.sakanal.MyBatis.F_SSM.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(){
        return userMapper.getUser();
    }
}
