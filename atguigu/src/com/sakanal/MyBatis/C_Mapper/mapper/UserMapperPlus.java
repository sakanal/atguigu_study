package com.sakanal.MyBatis.C_Mapper.mapper;

import com.sakanal.MyBatis.C_Mapper.bean.User;

import java.util.List;

public interface UserMapperPlus {
    User getUserById(Integer id);
    User getUserAndDept(Integer id);
    User getUserByIdStep(Integer id);
    List<User> getUserByDeptId(Integer id);
    User getUserByIdDis(Integer id);
}
