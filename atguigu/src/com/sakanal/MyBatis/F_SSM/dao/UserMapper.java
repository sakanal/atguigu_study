package com.sakanal.MyBatis.F_SSM.dao;

import com.sakanal.MyBatis.F_SSM.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserById(Integer id);
    List<User> getUser();
}
