package com.sakanal.JDBC.jdbc_1.preparedstatement;


import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/*
    使用PreparedStatement来代替  Statement，实现对数据表的增删改操作
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommonUpdate(){
//        String sql="delete from customers where id = ?";
//        update(sql,19);

        String sql="update `order` set order_name = ? where order_id = ?";
        update(sql,"DD","4");
    }

    //通用的增删改操作
    public void update(String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实列
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);//小心参数声明错误
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(connection,ps);
        };
    }


    //修改customers表的一条记录
    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实列
            String sql="update customers set name = ? where id = ?";
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1,"莫扎特");
            ps.setObject(2,18);
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(connection,ps);
        }
    }


    //向customers添加一条记录
    @Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取配置信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_1/preparedstatement/jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            //2.加载驱动
            Class.forName(driverClass);

            //3.获取连接
            connection = DriverManager.getConnection(url, user, password);

            //4.预编译sql语句，返回preparedstatement的实例
            String sql="insert into customers(name,email,birth)values(?,?,?)";  //?:占位符
            ps = connection.prepareStatement(sql);

            //5.填充占位符
            ps.setString(1,"哪吒");
            ps.setString(2,"nezha@gmail.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDateFormat.parse("1000-01-01");
            ps.setDate(3,new java.sql.Date(date.getTime()));

            //6.执行sql
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.资源的关闭
            try {
                if (ps!=null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
