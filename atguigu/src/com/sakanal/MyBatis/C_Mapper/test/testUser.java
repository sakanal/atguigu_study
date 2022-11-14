package com.sakanal.MyBatis.C_Mapper.test;

import com.sakanal.MyBatis.C_Mapper.bean.User;
import com.sakanal.MyBatis.C_Mapper.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testUser {
    /**
     *  1、mybatis允许增删改直接定义以下类型返回值：Integer，Long，Boolean
     *  2、增删改需要手动提交数据sqlSession.commit()
     *      sqlSessionFactory.openSession()==》手动提交
     *      sqlSessionFactory.openSession(true)==》自动提交
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/C_Mapper/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testGetUserById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(8);
        System.out.println(userById);
    }
    @Test
    public void testGetUserById1AndId2() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getUserById1AndId2(2, 7);
        list.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testGetUserByIdForMap() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map=new HashMap<>();
        map.put("id1",2);
        map.put("id2",7);
        map.put("tableName","user");
        List<User> list = mapper.getUserByIdForMap(map);
        list.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testGetUserByIdReturnMap()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = mapper.getUserByIdReturnMap(3);
        System.out.println(map);
        sqlSession.close();
    }
    @Test
    public void testGetUserByAddressLikeReturnMap()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<Integer, User> map = mapper.getUserByAddressLikeReturnMap("%a%");
        System.out.println(map);
        sqlSession.close();
    }
    @Test
    public void testGetUserAll() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userAll = mapper.getUserAll();
        userAll.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testInsertUser()throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setId(8);
            user.setName("黎海涛");
            user.setPassword("044815");
            user.setAddress("江西");
            user.setPhone("18870493682");
            boolean b = mapper.insertUser(user);
            System.out.println(b);
            //手动提交数据
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
//    @Test
//    public void testInsertUser1()throws IOException{
//        SqlSession sqlSession = getSqlSessionFactory().openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = new User(null,"黎海涛", "044815", "江西", "18870493682");
//        boolean b = mapper.insertUser1(user);
//        System.out.println(b);
//        sqlSession.commit();
//        sqlSession.close();
//    }
    @Test
    public void testUpdateUser()throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setId(8);
            user.setName("王小明");
            user.setPassword("123456");
            user.setAddress("湖北");
            user.setPhone("45678912350");
            boolean b = mapper.updateUser(user);
            System.out.println(b);
            //手动提交数据
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteUserById()throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            boolean b = mapper.deleteUserById(8);
            System.out.println(b);
            //手动提交数据
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
}
