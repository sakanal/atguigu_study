package com.sakanal.boot;

import com.sakanal.boot.bean.Person;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private Person person;

    @Value("${name}")
    private String name;
    @Value("${personTest.name}")
    private String personTest;
    @Value("${person.animal[0]}")
    private String animal0;

    @Autowired
    private Environment environment;
    @Test
    void contextLoads() {
        System.out.println(person);
    }
    @Test
    void testValue(){
        System.out.println("name1 = " + name);
        System.out.println("personTest = " + personTest);
        System.out.println("animal = " + animal0);
        System.out.println("------------------------");
        System.out.println(environment.getProperty("personTest.name"));
        System.out.println(environment.getProperty("personTest.address[0]"));
        System.out.println(environment.getProperty("person.userName"));
        System.out.println("------------------------");
        System.out.println("person = " + person);
    }

}
