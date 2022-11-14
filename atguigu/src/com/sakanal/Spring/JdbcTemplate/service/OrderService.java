package com.sakanal.Spring.JdbcTemplate.service;

import com.sakanal.Spring.JdbcTemplate.bean.Order;
import com.sakanal.Spring.JdbcTemplate.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public List<Order> queryOrder(){
        return orderDAO.queryOrder();
    }
}
