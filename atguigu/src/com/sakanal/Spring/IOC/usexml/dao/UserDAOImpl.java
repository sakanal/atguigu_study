package com.sakanal.Spring.IOC.usexml.dao;

import com.sakanal.Spring.IOC.usexml.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        String sql="select id,name,password,address from user";
        QueryRunner queryRunner = new QueryRunner();
        BeanListHandler<User> userBeanListHandler = new BeanListHandler<>(User.class);
        List<User> list = queryRunner.query(connection, sql, userBeanListHandler);
        return list;
    }
}
