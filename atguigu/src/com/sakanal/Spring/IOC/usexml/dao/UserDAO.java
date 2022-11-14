package com.sakanal.Spring.IOC.usexml.dao;

import com.sakanal.Spring.IOC.usexml.bean.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll(Connection connection) throws SQLException;
}
