package com.sakanal.MyBatis.C_Mapper.bean;

public class User {
    private Integer id;
    private String name;
    private String gender;
    private String password;
    private String address;
    private String phone;
    private Department department;

    public User() {
    }

    public User(Integer id, String name, String gender, String password, String address, String phone, Department department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                '}';
    }
}
