package com.sakanal.Spring.AOP.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class PersonProxy {

    @Before("com.sakanal.Spring.AOP.annotation.UserProxy.pointdemo()")
    public void before(){
        System.out.println("PersonProxy.before...");
    }
}
