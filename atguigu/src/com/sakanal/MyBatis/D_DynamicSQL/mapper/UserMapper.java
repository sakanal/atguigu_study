package com.sakanal.MyBatis.D_DynamicSQL.mapper;

import com.sakanal.MyBatis.D_DynamicSQL.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getUserTestInnerParameter(User user);
    List<User> getUserByConditionIf(User user);
    List<User> getUserByConditionTrim(User user);
    List<User> getUserByConditionChoose(User user);
    List<User> getUserByConditionForeach(@Param("ids") List<Integer> ids);
    void updateUser(User user);
    void insertUsers(@Param("users") List<User> users);
}
