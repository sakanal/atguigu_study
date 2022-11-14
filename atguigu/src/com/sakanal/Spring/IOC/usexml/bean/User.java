package com.sakanal.Spring.IOC.usexml.bean;

/**
 * 基于xml方式注入属性
 * 1、DI：依赖注入，就是注入属性
 *  （1）使用set方式进行注入
 *
 *  （2）使用有参构造进行注入
 *
 */
public class User {

    private int id;
    private String name;
    private String password;
    private String address;

    public User() {
        super();
    }

    public User(int id, String name, String password,String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address=address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void add(){
        System.out.println("add......");
    }
}
