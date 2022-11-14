package com.sakanal.Spring.JdbcTemplate.dao;

import com.sakanal.Spring.JdbcTemplate.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    //注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate test(){
        return jdbcTemplate;
    }

    @Override
    public void insert(User user) {
        //创建sql语句
        String sql="insert into user(id,name,password,address,phone)values(?,?,?,?,?)";
        //调用方法实现    6,"黎海涛","044815","江西","18870493682"
        Object[] args={user.getId(), user.getName(), user.getPassword(), user.getAddress(), user.getPhone()};
        //第一个参数：sql 语句
        //第二个参数：可变参数，设置 sql 语句值
        int update = jdbcTemplate.update(sql,args);
        System.out.println("update = " + update);
    }

    @Override
    public void update(User user) {
        //创建sql语句
        String sql="update user set name=?,password=?,phone=? where id=?";
        //调用方法实现
        Object[] args={user.getName(), user.getPassword(), user.getPhone(),user.getId()};
        int update = jdbcTemplate.update(sql,args);
        System.out.println("update = " + update);
    }

    @Override
    public void delete(String id) {
        //创建sql语句
        String sql="delete from user where id=?";
        //调用方法实现
        int update = jdbcTemplate.update(sql,id);
        System.out.println("update = " + update);

    }

    @Override
    public int selectCount() {
        String sql="select count(*) from user";
        //第一个参数：sql 语句
        //第二个参数：返回类型 Class
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }

    @Override
    public User queryUser(String id){
        String sql="select * from user where id=?";
        //第一个参数：sql 语句
        //第二个参数：RowMapper 是接口，针对返回不同类型数据，使用这个接口里面实现类完成数据封装
        //第三个参数：sql 语句值
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    @Override
    public List<User> queryUserForList() {
        String sql="select * from user";
        //第一个参数：sql 语句
        //第二个参数：RowMapper 是接口，针对返回不同类型数据，使用这个接口里面实现类完成数据封装
        //第三个参数：sql 语句值
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public void batchInsert(List<Object[]> batchArgs) {
        String sql="insert into user(id,name,password,address,phone)values(?,?,?,?,?)";
        //第一个参数：sql 语句
        //第二个参数：List 集合，添加多条记录数据
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        String sql="update user set name=?,password=?,phone=? where id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void batchDelete(List<Object[]> batchArgs) {
        String sql="delete from user where id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }
}
