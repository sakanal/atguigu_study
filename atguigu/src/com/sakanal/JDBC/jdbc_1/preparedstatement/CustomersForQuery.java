package com.sakanal.JDBC.jdbc_1.preparedstatement;

import com.sakanal.JDBC.jdbc_1.bean.Customer;
import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对于Customers表的查询
 */
public class CustomersForQuery {

    @Test
    public void testQueryForCustomers(){
        String sql="select name,id from customers where id = 13";
        Customer customer = queryForCustomers(sql);
        System.out.println(customer);
    }

    public Customer queryForCustomers(String sql,Object ...args){
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
            //获取结果集的元数据:ResultSetMetaData
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            if(resultSet.next()){
                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = metaData.getColumnName(i + 1);
                    //给customer对象指定的columnName属性，赋值为columnvalue:通过反射
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer,columnValue);
                }
            return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Test
    public void testQuery1(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,1);
            //执行并返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            if (resultSet.next()) {//判断结果集的下一条是否有数据，如果有数据返回true，并指针下移；如果返回false，指针不会下移动，结束。
                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                //方式一
    //            System.out.println("id = "+id+" name = "+name+" email = "+email+", birth = "+ birth);

                //方式二
    //            Object[] date=new Object[]{id,name,email,birth};

                //方式三：将数据封装为一个对象
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(connection,ps,resultSet);
        }

    }
}
