package com.sakanal.JDBC.jdbc_1.preparedstatement;

import com.sakanal.JDBC.jdbc_1.bean.Order;
import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 针对于Order表的通用查询操作
 */
public class OrderForQuery {
    /**
     * 针对于表的字段名与类的属性名不相同的情况：
     * 1.必须在声明sql时，使用类的属性名来命名字段的别名
     * 2.使用ResultSetMetaData时，需要使用getColumnLabel()来替换getColumnName()，获取列的别名
     * 说明：如果sql中没有给字段其别名，getColumnLabel()获取的就是列名
     */

    @Test
    public void testorderForQuery(){
        String sql="select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }

    public Order orderForQuery(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //执行，获取结果集
            resultSet = preparedStatement.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()){
                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的列值：通过结果集          ResultSet
                    Object columnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名：通过结果集的元数据    ResultSetMetaData
                    //获取数据库中列的列名：getColumnName()    --不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    //获取数据库中列的别名：getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    //通过反射将对象，将对象指定名columnName的属性赋值为指定值columnValue
                    //通过在sql语句中对数据库中列表起别名的方式，使得从数据库中查找到的结果集中的列名可以于java类中的声明对应
                    //数据库：order_id      java类：orderId
                    Field field = Order.class.getDeclaredField(columnLabel);
                    //保证field是私有的也可以访问
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        return null;
    }
}
