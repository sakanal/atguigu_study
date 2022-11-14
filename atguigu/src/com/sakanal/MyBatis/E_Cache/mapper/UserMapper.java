package com.sakanal.MyBatis.E_Cache.mapper;

import com.sakanal.MyBatis.E_Cache.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserById(Integer id);
    List<User> getUser();
}
