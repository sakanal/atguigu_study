package com.sakanal.mybatisPlus.service.impl;

import com.sakanal.mybatisPlus.entity.User;
import com.sakanal.mybatisPlus.mapper.UserMapper;
import com.sakanal.mybatisPlus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }
}
