package com.sakanal.mvc.demo2.dao;

import com.sakanal.mvc.demo2.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserAll();
    boolean insertUser(User user);
}
