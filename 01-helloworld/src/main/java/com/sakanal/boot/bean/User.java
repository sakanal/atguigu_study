package com.sakanal.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private Pet pet;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
