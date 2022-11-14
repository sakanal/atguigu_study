package com.sakanal.Spring.JdbcTemplate.dao;

import com.sakanal.Spring.JdbcTemplate.bean.User;

import java.util.List;

public interface UserDAO {
    //添加方法
    void insert(User user);
    //修改方法
    void update(User user);
    //删除方法
    void delete(String id);
    //查询表记录数
    int selectCount();
    //查询单个对象
    User queryUser(String id);
    //查询返回所有对象集合
    List<User> queryUserForList();
    //批量添加
    void batchInsert(List<Object[]> batchArgs);
    //批量修改
    void batchUpdate(List<Object[]> batchArgs);
    //批量删除
    void batchDelete(List<Object[]> batchArgs);
}
