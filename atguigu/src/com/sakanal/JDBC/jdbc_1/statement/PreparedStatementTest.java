package com.sakanal.JDBC.jdbc_1.statement;

import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * 演示使用PreparedStatement替换Statement，解决SQL注入问题
 *
 * PreparedStatement的其他好处：
 * 1.PreparedStatement可以操作Blob的数据，二Statement做不到
 * 2.PreparedStatement可以实现更高效的批量操作
 */
public class PreparedStatementTest {

    @Test
    public static void testLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String user=scanner.nextLine();
        System.out.println("请输入密码:");
        String password=scanner.nextLine();

        //sql注入：
        //select user,password from user_table where user='1' or ' and password='=1 or '1'='1'
        String sql="select user,password from user_table where user= ? and password=?";
        User returnUser = getInstance(User.class,sql,user,password);

        if (returnUser!=null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

    public static <T>T getInstance(Class<T> clazz, String sql, Object... args){
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
            if (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) {
        testLogin();
    }
}
