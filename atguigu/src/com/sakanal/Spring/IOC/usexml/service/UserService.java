package com.sakanal.Spring.IOC.usexml.service;

import com.sakanal.Spring.IOC.usexml.bean.User;
import com.sakanal.Spring.IOC.usexml.dao.UserDAO;
import com.sakanal.Spring.IOC.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    //创建UserDAO类型属性，生成set方法
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void getAll() throws SQLException {
        System.out.println("UserService getAll.............");
        Connection connection = JDBCUtils.getConnection();

        //原始方式：创建UserDAO对象
//        UserDAO userDAO = new UserDAOImpl();


        List<User> list = userDAO.getAll(connection);
        list.forEach(System.out::println);

    }
}
