package com.sakanal.boot.service.impl;

import com.sakanal.boot.dao.UserMapper;
import com.sakanal.boot.entity.User;
import com.sakanal.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAllUsers() {
        List<User> users = userMapper.selectByExample(null);
        return users;
    }
}
