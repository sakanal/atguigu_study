package com.sakanal.boot;

import com.sakanal.config.EnableUser;
import com.sakanal.config.MyImportBeanDefinitionRegistrar;
import com.sakanal.config.MyImportSelector;
import com.sakanal.config.UserConfig;
import com.sakanal.entity.Role;
import com.sakanal.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Map;

/*
   @ComponentScan 扫描范围：当前引导类所在包及子包
 *
 *  com.sakanal.boot
 *  com.sakanal.config
 *  //1、使用@ComponentScan扫描com.sakanal.config包
 *  //2、可以使用@Import注解，加载类。这些类都会被Spring创建，并放入IOC容器
 *  //3、可以对@Import注解进行封装
 */

/**
 *  @Import 的四种用法
 *  1.导入Bean
 *  2.导入配置类
 *  3.导入ImportSelector的实现类
 *  4.导入ImportBeanDefinitionRegistrar的实现类
 */

//@ComponentScan("com.sakanal.config")
//@Import(UserConfig.class)
//@EnableUser

//@Import(User.class)
//@Import(UserConfig.class) //配置类上的@Configuration可以不加
//@Import(MyImportSelector.class)
@Import(MyImportBeanDefinitionRegistrar.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//        //1
//        Object user = run.getBean("user");
//        System.out.println("user = " + user);

        //2
        User user = run.getBean(User.class);
        Role role = run.getBean(Role.class);
        System.out.println("user = " + user);
        System.out.println("role = " + role);
        Map<String, User> beansOfType = run.getBeansOfType(User.class);
        System.out.println("beansOfType = " + beansOfType); //beansOfType = {com.sakanal.entity.User=com.sakanal.entity.User@4879dfad}
    }

}
