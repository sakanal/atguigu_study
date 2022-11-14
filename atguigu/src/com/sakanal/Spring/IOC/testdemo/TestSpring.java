package com.sakanal.Spring.IOC.testdemo;

import com.sakanal.Spring.IOC.usexml.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void testAdd(){
        //1.加载Spring的配置文件
        //Spring提供IOC容器实现两种方式：（两个接口）
        //BeanFactory：IOC容器基本实现，是Spring内部接口，不提供开放人员进行使用
        //加载配置文件的时候，不会创建对象，在获取/使用的时候才会创建对象
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/sakanal/Spring/xml/userBean.xml");

        //ApplicationContext：BeanFactory接口的子接口，提供更多更强大的功能，一般由开发人员进行使用
        //加载配置文件的时候，就会将配置文件中的对象进行创建     <bean id="userBean" class="com.sakanal.Spring.bean.User"></bean>
        //在项目/服务器启动时创建好对象更合适
        //ClassPathXmlApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/userBean.xml");
        //FileSystemXmlApplicationContext
//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\Program Files (x86)\\Developer_tools\\IDEAworkspace\\Project01\\atguigu\\src\\com\\sakanal\\Spring\\xml\\userBean.xml");

        //2.获取配置创建的对象
        User user = applicationContext.getBean("userBean", User.class);
        System.out.println(user);
        user.add();
        //
    }
}
