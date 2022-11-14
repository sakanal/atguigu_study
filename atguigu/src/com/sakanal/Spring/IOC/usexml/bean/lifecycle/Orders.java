package com.sakanal.Spring.IOC.usexml.bean.lifecycle;

public class Orders {
    private String name;
    //无参数构造
    public Orders() {
        super();
        System.out.println("第一步 执行无参数构造创建 bean 实例");
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("第二步 调用 set 方法设置属性值");
    }
    //创建执行的初始化的方法
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法");
    }
    //创建执行的销毁的方法
    public void destroyMethod() {
        System.out.println("第五步 执行销毁的方法");
    } }