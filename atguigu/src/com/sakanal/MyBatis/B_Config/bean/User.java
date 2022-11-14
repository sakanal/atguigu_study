package com.sakanal.MyBatis.B_Config.bean;

import org.apache.ibatis.type.Alias;

//起别名，防止在mybatis-config.xml中批量起别名时，父类包和子类包有相同命名的类在批量起别名时有默认相同，报错
//@Alias==typeAlias>package
@Alias("User")
public class User {
    private int id;
    private String name;
    private String password;
    private String address;
    private String phone;

    public User() {
    }

    public User(int id, String name, String password, String address, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
