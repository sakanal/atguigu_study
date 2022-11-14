package com.sakanal.Spring.JdbcTemplate.dao;

import com.sakanal.Spring.JdbcTemplate.bean.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> queryOrder();
}
