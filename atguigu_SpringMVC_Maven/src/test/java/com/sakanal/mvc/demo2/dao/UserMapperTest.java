//package com.sakanal.mvc.demo2.dao;
//
//import com.sakanal.mvc.demo2.pojo.User;
//import com.sakanal.mvc.demo2.utils.MyBatisUtils;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class UserMapperTest {
//    private static MyBatisUtils myBatisUtils=new MyBatisUtils();
//    @Test
//    public void testGetUserAll(){
//        SqlSession sqlSession = myBatisUtils.getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        try {
//            List<User> userAll = mapper.getUserAll();
//            userAll.forEach(System.out::println);
//        }finally {
//            myBatisUtils.closeSqlSession(sqlSession);
//        }
//    }
//}