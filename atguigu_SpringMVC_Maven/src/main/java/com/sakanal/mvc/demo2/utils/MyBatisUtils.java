//package com.sakanal.mvc.demo2.utils;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@Component
//public class MyBatisUtils {
//    private static SqlSessionFactory sqlSessionFactory;
//    static {
//        try {
//            InputStream inputStream = Resources.getResourceAsStream("demo2/mybatis-config.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Autowired
//    @Qualifier("sqlSessionTemplate")
//    private SqlSessionTemplate sqlSessionTemplate;
//    public SqlSession getSqlSession(){
//        return sqlSessionFactory.openSession();
//    }
//    public void closeSqlSession(SqlSession sqlSession){
//        if ( sqlSession != null) {
//            sqlSession.close();
//        }
//    }
//}
