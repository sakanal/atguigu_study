package com.sakanal.Spring.IOC.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource source;
    static{
        try {
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("com/sakanal/Spring/IOC/properties/druid.properties");
            properties.load(is);
            source= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }
//
    private static DataSource dataSource;
    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/Druid.xml");
        dataSource = classPathXmlApplicationContext.getBean("dataSource", DataSource.class);
    }
    public static Connection getConnection1() throws SQLException{
        Connection connection = dataSource.getConnection();
        return connection;
    }
    @Test
    public void test() throws SQLException {
        System.out.println(source.getConnection());
    }
//
    public static void closeResource(Connection connection, Statement statement){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statement);
    }
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statement);
        DbUtils.closeQuietly(resultSet);
    }
}
