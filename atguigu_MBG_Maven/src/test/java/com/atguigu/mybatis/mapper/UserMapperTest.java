package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.pojo.UserExample;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UserMapperTest extends TestCase {
    public static SqlSession getSqlSession() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory.openSession();
    }
    @Test
    public void testMBG() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        //名字为张三，且年龄大于等于20
        example.createCriteria().andNameEqualTo("张三").andGenderEqualTo("1");
        //或者did不为空
//        example.or().andDepartmentIdIsNotNull();
        List<User> emps = mapper.selectByExample(example);
        emps.forEach(System.out::println);
    }


}