package com.sakanal.Spring.IOC.testdemo;

import com.sakanal.Spring.IOC.usexml.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanManagementBasedXML {

    @Test
    public void testCreateObject(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/userBean.xml");
        User user1 = classPathXmlApplicationContext.getBean("userBean", User.class);
        User user2 = classPathXmlApplicationContext.getBean("userSet", User.class);
        User user3 = classPathXmlApplicationContext.getBean("userStructure", User.class);
        User user4 = classPathXmlApplicationContext.getBean("userP", User.class);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
    }
}
