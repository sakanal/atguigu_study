package com.sakanal.Spring.JdbcTemplate.testdemo;

import com.sakanal.Spring.JdbcTemplate.bean.Order;
import com.sakanal.Spring.JdbcTemplate.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testOrder {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/JdbcTemplate/Druid.xml");
        OrderService orderService = classPathXmlApplicationContext.getBean("orderService", OrderService.class);
        List<Order> orderList = orderService.queryOrder();
        orderList.forEach(System.out::println);
    }
}
