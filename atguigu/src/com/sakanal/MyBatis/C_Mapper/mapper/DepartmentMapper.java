package com.sakanal.MyBatis.C_Mapper.mapper;

import com.sakanal.MyBatis.C_Mapper.bean.Department;

public interface DepartmentMapper {
    Department getDepartmentById(Integer id) ;
    Department getDepartmentByIdPlus(Integer id);
    Department getDepartmentByIdStep(Integer id);
}
