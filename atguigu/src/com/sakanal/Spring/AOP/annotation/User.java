package com.sakanal.Spring.AOP.annotation;

import org.springframework.stereotype.Component;

//被增强的类
@Component
public class User {

    public void add(){
        System.out.println("add...");
        int i = 1/0;
    }
}
