package com.sakanal.MyBatis.B_Config.test;

import com.sakanal.MyBatis.B_Config.bean.User;
import com.sakanal.MyBatis.B_Config.mapper.UserMapper;
import com.sakanal.MyBatis.B_Config.mapper.UserMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class UserTest {
    public SqlSessionFactory getSqlSessionFactory()throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/B_Config/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper;
        try {
            mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperAnnotation mapper = sqlSession.getMapper(UserMapperAnnotation.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }
}