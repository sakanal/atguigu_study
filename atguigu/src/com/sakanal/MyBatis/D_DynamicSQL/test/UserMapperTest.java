package com.sakanal.MyBatis.D_DynamicSQL.test;

import com.sakanal.MyBatis.D_DynamicSQL.mapper.UserMapper;
import com.sakanal.MyBatis.D_DynamicSQL.pojo.Department;
import com.sakanal.MyBatis.D_DynamicSQL.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserMapperTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/D_DynamicSQL/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testGetUserByConditionIf()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User(null,"LadyGaGa","123456","0","America","13012386565");
            List<User> list = mapper.getUserByConditionIf(user);
            list.forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserByConditionTrim()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User(null,"LadyGaGa","123456","0","","13012386565");
            List<User> list = mapper.getUserByConditionTrim(user);
            list.forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserByConditionChoose()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User(null,"LadyGaGa","123456","0","","13012386565");
            List<User> list = mapper.getUserByConditionChoose(user);
            list.forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserByConditionForeach()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            List<User> list = mapper.getUserByConditionForeach(Arrays.asList(1,2,3));
            list.forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserTestInnerParameter()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User();
            //查找手机尾号为5的用户，将模糊查询使用的%，在sql映射文件中使用bind进行修改绑定
            user.setPhone("5");
            List<User> list = mapper.getUserTestInnerParameter(user);
            list.forEach(System.out::println);
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
    @Test
    public void testUpdateUser()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user = new User(8,null,null,"1","南康","13012386565");
            mapper.updateUser(user);
            sqlSession.commit();
            List<User> list = mapper.getUserByConditionTrim(user);
            list.forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testInsertUsers()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            ArrayList<User> users = new ArrayList<>();
            users.add(new User(null,"黎海涛1","123456","1","南康","13012386565",new Department(1,"研发部")));
            users.add(new User(null,"黎海涛2","123456","1","南康","13012386565",new Department(1,"研发部")));
            users.add(new User(null,"黎海涛3","123456","1","南康","13012386565",new Department(1,"研发部")));
            mapper.insertUsers(users);
            sqlSession.commit();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}