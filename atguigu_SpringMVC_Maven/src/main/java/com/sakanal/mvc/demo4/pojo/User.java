package com.sakanal.mvc.demo4.pojo;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String gender;
    private String address;
    private String phone;
    private Integer department_id;

    public User() {
    }

    public User(Integer id, String name, String password, String gender, String address, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public User(Integer id, String name, String password, String gender, String address, String phone, Integer department_id) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.department_id = department_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", department_id=" + department_id +
                '}';
    }
}
