package com.sakanal.Spring.JdbcTemplate.bean;

import java.util.Date;

/**
 * 表中为order_id
 * 在使用select * from order时
 * 可以为order_id或orderId,但是不可以是id
 *
 * select order_id id,order_name name,order_date date from `order`
 * 可以使用别名的方式
 */
public class Order {


//    private int orderId;
//    private String orderName;
//    private Date orderDate;
//
//    public Order() {
//    }
//
//    public Order(int orderId, String orderName, Date orderDate) {
//        this.orderId = orderId;
//        this.orderName = orderName;
//        this.orderDate = orderDate;
//    }
//
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getOrderName() {
//        return orderName;
//    }
//
//    public void setOrderName(String orderName) {
//        this.orderName = orderName;
//    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId=" + orderId +
//                ", orderName='" + orderName + '\'' +
//                ", orderDate=" + orderDate +
//                '}';
//    }




    private int id;
    private String name;
    private Date date;

    public Order() {
    }

    public Order(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
