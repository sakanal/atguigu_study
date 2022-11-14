package com.sakanal.Spring.JdbcTemplate.dao;

import com.sakanal.Spring.JdbcTemplate.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> queryOrder() {
        String sql="select order_id id,order_name name,order_date date from `order`";
        List<Order> orderList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        return orderList;
    }
}
