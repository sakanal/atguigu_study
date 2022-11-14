package com.sakanal.test;

import com.sakanal.boot.Application;
import com.sakanal.boot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {
    @Autowired
    private Person person;
    @Test
    public void test(){
        System.out.println("person = " + person);
    }
}
