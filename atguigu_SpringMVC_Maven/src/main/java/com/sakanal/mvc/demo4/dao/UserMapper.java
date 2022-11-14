package com.sakanal.mvc.demo4.dao;

import com.sakanal.mvc.demo4.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> getUsers();
    public List<User> getUserByLike(User user);
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
