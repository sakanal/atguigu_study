package com.sakanal.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user",autoResultMap = true)
public class User {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
//    @TableField(value = "create_time",fill = FieldFill.INSERT,typeHandler = MyTypeHandler.class)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;
//    @TableField(value = "update_time",fill = FieldFill.UPDATE,typeHandler = MyTypeHandler.class)
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
