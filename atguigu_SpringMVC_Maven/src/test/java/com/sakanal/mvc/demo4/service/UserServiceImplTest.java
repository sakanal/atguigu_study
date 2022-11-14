package com.sakanal.mvc.demo4.service;

import com.sakanal.mvc.demo4.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:demo4/applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testGetUsers(){
        List<User> users = userService.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    public void testGetUserByLike() {
        User user = new User();
//        user.setName("海");
        user.setPassword("456");
        List<User> users = userService.getUserByLike(user);
        users.forEach(System.out::println);
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("海");
        user.setPassword("123789");
        userService.insertUser(user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(19);
        user.setName("海");
        user.setPassword("000");
        userService.updateUser(user);
    }

    @Test
    public void deleteUser(){
        User user = new User();
        user.setId(30);
        userService.deleteUser(user);
    }

}