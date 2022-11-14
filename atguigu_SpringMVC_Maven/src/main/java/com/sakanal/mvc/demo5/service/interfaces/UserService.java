package com.sakanal.mvc.demo5.service.interfaces;

import com.sakanal.mvc.demo5.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public List<User> getUserByLike(User user);
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
