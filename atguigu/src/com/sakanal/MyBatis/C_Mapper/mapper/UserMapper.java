package com.sakanal.MyBatis.C_Mapper.mapper;

import com.sakanal.MyBatis.C_Mapper.bean.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取单个记录
    User getUserById(Integer id);
    List<User> getUserById1AndId2(@Param("id1")Integer id1,@Param("id2")Integer id2);
    List<User> getUserByIdForMap(Map<String,Object> map);
    //返回一条记录的map：key就是列名，value就是对应的值
    @MapKey("users")
    Map<String,Object> getUserByIdReturnMap(int id);
    //多条记录封装一个map：Map<int,User>：key是这条记录的主键，value是记录封装后的javabean对象
    //高数mybatis封装map的时候用哪个属性作为nap的key
    @MapKey("id")
    Map<Integer,User> getUserByAddressLikeReturnMap(String address);
    //获取所有记录
    List<User> getUserAll();
    //添加一条记录
    boolean insertUser(User user);
    boolean insertUser1(User user);
    //修改一条记录
    boolean updateUser(User user);
    //删除一条记录
    boolean deleteUserById(int id);

}
