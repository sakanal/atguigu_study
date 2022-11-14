package com.sakanal.Spring.IOC.useannotation.service;

import com.sakanal.Spring.IOC.useannotation.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//在注解里面的value属性值可以省略不写
//默认值是类名称，首字母会小写（userService）
//@Component(value = "userService")   //<bean id="userService" class="..."/>
//@Controller
//@Repository
@Service
public class UserService {

    @Value(value = "Tom")
    private String name;

    //定义dao类型的属性
    //不需要添加set方法
    //添加注入属性注解

//    @Autowired      //根据类型进行注入
//    @Qualifier(value = "userDAOImpl")
//    private UserDAO userDAO;

//    @Resource         //根据类型进行注入
    @Resource(name = "userDAOImpl")     //根据名称进行注入
    private UserDAO userDAO;

    public void add(){
        System.out.println("UserService add........."+name);
        userDAO.add();
    }
}
