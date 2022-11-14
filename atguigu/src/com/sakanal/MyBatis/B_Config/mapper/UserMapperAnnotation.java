package com.sakanal.MyBatis.B_Config.mapper;

import com.sakanal.MyBatis.B_Config.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapperAnnotation {
    @Select("select * from `user` where id=#{id}")
    User getUserById(int id);
}
