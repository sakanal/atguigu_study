package com.sakanal.JDBC.jdbc_2.dao.optimization;

import com.sakanal.JDBC.jdbc_2.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用于规范针对于customers表的常用操作
 */
public interface CustomerDAO {

    //将customer对象添加到数据库中
    void insert(Connection connection, Customer customer);

    //根据指定的id，删除表中的一条记录
    void deleteById(Connection connection,int id);

    //针对于内存中的customer对象，去修改数据表中指定的记录
    void update(Connection connection,Customer customer);

    //针对于指定的id查询得到对应的Customer对象
    Customer getCustomerById(Connection connection,int id);

    //查询表中的所有记录构成的集合
    List<Customer> getAll(Connection connection);

    //返回数据表中的数据条目数
    Long getCount(Connection connection);

    //返回数据表中的最大的生日
    Date getMaxBirth(Connection connection);
}
