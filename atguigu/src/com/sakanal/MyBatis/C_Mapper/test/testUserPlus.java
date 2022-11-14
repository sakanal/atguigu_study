package com.sakanal.MyBatis.C_Mapper.test;

import com.sakanal.MyBatis.C_Mapper.bean.Department;
import com.sakanal.MyBatis.C_Mapper.bean.User;
import com.sakanal.MyBatis.C_Mapper.mapper.DepartmentMapper;
import com.sakanal.MyBatis.C_Mapper.mapper.UserMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class testUserPlus {
    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("com/sakanal/MyBatis/C_Mapper/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void testGetUserById()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
        try {
            User userById = mapper.getUserById(2);
            System.out.println(userById);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserAndDept()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
        try {
            User user = mapper.getUserAndDept(5);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserByIdStep()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
        try {
            User user = mapper.getUserByIdStep(5);
            System.out.println(user.getId());
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetDepartmentByIdPlus() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        try {
            Department department = mapper.getDepartmentByIdPlus(2);
            System.out.println(department);
            department.getUserList().forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetDepartmentByIdStep()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        try {
            Department department = mapper.getDepartmentByIdStep(2);
            System.out.println(department);
            department.getUserList().forEach(System.out::println);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetUserByIdDis() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapperPlus mapper = sqlSession.getMapper(UserMapperPlus.class);
        try {
            User user = mapper.getUserByIdDis(8);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }
}
