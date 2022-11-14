package com.sakanal.MyBatis.E_Cache.pojo;

import com.sakanal.MyBatis.D_DynamicSQL.pojo.User;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
//    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private List<com.sakanal.MyBatis.D_DynamicSQL.pojo.User> userList;

    public Department() {
    }

    public Department(Integer id, String name, List<com.sakanal.MyBatis.D_DynamicSQL.pojo.User> userList) {
        this.id = id;
        this.name = name;
        this.userList = userList;
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.sakanal.MyBatis.D_DynamicSQL.pojo.User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
