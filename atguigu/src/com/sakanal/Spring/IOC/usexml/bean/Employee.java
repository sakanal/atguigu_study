package com.sakanal.Spring.IOC.usexml.bean;

//员工类
public class Employee {
    private String name;
    private String sex;

    //员工属于某一个部门，使用对象形式表示
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", department=" + department +
                '}';
    }
}
