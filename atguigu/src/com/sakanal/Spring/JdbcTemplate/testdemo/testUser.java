package com.sakanal.Spring.JdbcTemplate.testdemo;

import com.sakanal.Spring.JdbcTemplate.bean.User;
import com.sakanal.Spring.JdbcTemplate.dao.UserDAOImpl;
import com.sakanal.Spring.JdbcTemplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class testUser {

    /**
     * 这种方法果然可以获得connection
     * 当初没成功是因为在xml文件配置连接池时写错属性名
     * idea自动生成有两个：username和name
     * 正确使用应该是username
     * idea误我
     */
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserDAOImpl userDAOImpl = classPathXmlApplicationContext.getBean("userDAOImpl", UserDAOImpl.class);
        JdbcTemplate jdbcTemplate = userDAOImpl.test();
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    //测试添加
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        User user = new User();
        user.setId(6);
        user.setName("黎海涛");
        user.setPassword("044815");
        user.setAddress("江西");
        user.setPhone("18870493682");
        userService.insertUser(user);
    }

    //测试修改
    @Test
    public void test2(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        User user = new User();
        user.setId(7);
        user.setName("黄威");
        user.setPassword("123456");
        user.setAddress("江西");
        user.setPhone("19874568204");
        userService.updateUser(user);
    }

    //测试删除
     @Test
    public void test3(){
         ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
         UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
         userService.deleteUser("8");
     }

     //查询返回某个值
    @Test
    public void test4(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        int count = userService.findCount();
        System.out.println("count = " + count);
    }

     //查询返回对象
    @Test
    public void test5(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        User user = userService.queryUser("2");
        System.out.println(user);
    }

     //查询返回对象集合
    @Test
    public void test6(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        List<User> userList = userService.queryUserForList();
        userList.forEach(System.out::println);
    }

    //批量添加
    @Test
    public void test7(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        ArrayList<Object[]> batchArgs = new ArrayList<>();
//        Object[] objects1={8,"黎海涛","044815","江西","18870493682"};
//        Object[] objects2={9,"黎海涛","044815","江西","18870493682"};
//        Object[] objects3={10,"黎海涛","044815","江西","18870493682"};
//        batchArgs.add(objects1);
//        batchArgs.add(objects2);
//        batchArgs.add(objects3);
        for (int i = 8; i < 11; i++) {
            Object[] objects={i,"黎海涛","044815","江西","18870493682"};
            batchArgs.add(objects);
        }
        userService.batchInsert(batchArgs);
    }
    //批量修改
    @Test
    public void test8(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        ArrayList<Object[]> batchArgs = new ArrayList<>();
        for (int i = 8; i < 11; i++) {
            Object[] objects={"小明","123456","18874569852",i};
            batchArgs.add(objects);
        }
        userService.batchUpdate(batchArgs);
    }
    //批量删除
    @Test
    public void test9(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        ArrayList<Object[]> batchArgs = new ArrayList<>();
        for (int i = 8; i < 11; i++) {
            Object[] objects={i};
            batchArgs.add(objects);
        }
        userService.batchDelete(batchArgs);
    }
}
