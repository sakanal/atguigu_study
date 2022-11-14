package com.sakanal.mvc.demo5.service;

import com.sakanal.mvc.demo5.dao.UserMapper;
import com.sakanal.mvc.demo5.pojo.User;
import com.sakanal.mvc.demo5.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public List<User> getUserByLike(User user) {
        return userMapper.getUserByLike(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);

    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }
}
