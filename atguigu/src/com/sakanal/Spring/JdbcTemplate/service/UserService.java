package com.sakanal.Spring.JdbcTemplate.service;

import com.sakanal.Spring.JdbcTemplate.bean.User;
import com.sakanal.Spring.JdbcTemplate.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //注入DAO
    @Autowired
    private UserDAO userDAO;

    //添加方法
    public void insertUser(User user){
        userDAO.insert(user);
    }
    //修改方法
    public void updateUser(User user){
        userDAO.update(user);
    }
    //删除方法
    public void deleteUser(String id){
        userDAO.delete(id);
    }
    //查询表记录数
    public int findCount(){return userDAO.selectCount();}
    //查询单个对象
    public User queryUser(String id){return userDAO.queryUser(id);}
    //查询返回所有对象集合
    public List<User> queryUserForList(){return userDAO.queryUserForList();}
    //批量添加
    public void batchInsert(List<Object[]> batchArgs){userDAO.batchInsert(batchArgs);}
    //批量修改
    public void batchUpdate(List<Object[]> batchArgs){userDAO.batchUpdate(batchArgs);}
    //批量删除
    public void batchDelete(List<Object[]> batchArgs){userDAO.batchDelete(batchArgs);}
}
