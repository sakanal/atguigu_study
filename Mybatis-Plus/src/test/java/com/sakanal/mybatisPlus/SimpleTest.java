package com.sakanal.mybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.mybatisPlus.entity.User;
import com.sakanal.mybatisPlus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@ComponentScan("com.sakanal.mybatisPlus.handler")
public class SimpleTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> userList = userMapper.selectList(null);
        for (User user:userList){
            System.out.println(user);
        }
    }
    @Test
    public void testSelectPage(){
        Page<User> userPage = new Page<>(1, 1);
        userMapper.selectPage(userPage,null);

        System.out.println(userPage.getPages());
        System.out.println(userPage.getSize());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getCurrent());
        userPage.getRecords().forEach(System.out::println);

    }

    @Test
    public void testInsert(){
        User user = new User("杜牧", 25, "dumu@gmail.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void testUpdate(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name","杜牧");
        User user = new User(null, null, "dumu@gmail.com");
        userMapper.update(user,userQueryWrapper);
    }

    @Test
    public void testBatchDelete(){
        userMapper.delete(null);
    }

}
