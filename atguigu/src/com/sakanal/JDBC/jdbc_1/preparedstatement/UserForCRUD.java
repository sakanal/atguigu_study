package com.sakanal.JDBC.jdbc_1.preparedstatement;

import com.sakanal.JDBC.jdbc_1.bean.User;
import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class UserForCRUD {

    @Test
    public void testQuery(){
        String sql="select id userId,name userName,password userPassword,address userAddress,phone userPhone from user";
        queryTest(sql);
    }
    @Test
    public void testInsert(){
        String sql="insert into user(id,name,password,address,phone)values(?,?,?,?,?)";
        updateTest(sql,6,"许嵩","xushong","Beijing","166598742365");
    }
    @Test
    public void testUpdate(){
        String sql="update user set password = ? where id = ?";
        updateTest(sql,"notxushong","6");
    }
    @Test
    public void testDelete(){
        String sql="delete from user where id = ?";
        updateTest(sql,6);
    }

    public void updateTest(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    public void queryTest(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                User user = new User();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = User.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(user,columnValue);
                }
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
    }
}
