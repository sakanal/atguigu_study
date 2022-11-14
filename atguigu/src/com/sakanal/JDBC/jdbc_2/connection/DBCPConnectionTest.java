package com.sakanal.JDBC.jdbc_2.connection;

import com.sakanal.JDBC.jdbc_2.bean.Customer;
import com.sakanal.JDBC.jdbc_2.connection.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class DBCPConnectionTest {
    @Test
    public void testQuery()throws Exception{
        Connection connection = JDBCUtils.getConnection2();
        String sql="select id,name,email,birth from customers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        ArrayList<Customer> list = new ArrayList<>();
        while (resultSet.next()){
            Customer customer = new Customer();
            for (int i = 0; i < columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i + 1);
                Object columnValue = resultSet.getObject(i + 1);
                Field field = Customer.class.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(customer,columnValue);
            }
            list.add(customer);
        }
        list.forEach(System.out::println);
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);
    }
}
