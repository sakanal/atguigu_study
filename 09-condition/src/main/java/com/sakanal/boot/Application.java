package com.sakanal.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        Object user = run.getBean("user");
        System.out.println("user = " + user);
        System.out.println("----------------------------");
        Object myUser = run.getBean("myUser");
        System.out.println("myUser = " + myUser);
    }

}
