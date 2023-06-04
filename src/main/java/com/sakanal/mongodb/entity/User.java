package com.sakanal.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "user")
public class User {
    @MongoId
    private Long id;
    @Field
    private String name;
    @Field
    private int age;
    @Field
    private String address;
    @Field
    private String phone;
}
