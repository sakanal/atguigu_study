package com.sakanal.JDBC.jdbc_2.connection.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_2/util/jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     *使用c3p0的数据库连接池技术
     * @return Connection
     * @throws Exception
     */
    //数据库连接池只需提供一个即可
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection1() throws SQLException {
        Connection connection = cpds.getConnection();
        return connection;
    }


    /**
     * 使用DBCP数据库连接池技术来获取数据库连接
     * @return Connection
     * @throws Exception
     */
    private static DataSource source;
    static {
        try {
            Properties properties = new Properties();
            FileInputStream is = new FileInputStream(new File("src/com/sakanal/JDBC/jdbc_2/connection/util/dbcp.properties"));
            properties.load(is);
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection2() throws Exception{
        Connection connection = source.getConnection();
        return connection;
    }


    /**
     * 使用Druid数据库连接池技术来获取数据库连接
     * @return Connection
     * @throws Exception
     */
    private static DataSource source1;
    static {
        try {
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_2/connection/util/druid.properties");
            properties.load(is);
            source1 = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection3() throws Exception{
        Connection connection = source1.getConnection();
        return connection;
    }



    //关闭数据库的连接
    public static void closeResource1(Connection connection, Statement statement){
        try {
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource1(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement!=null) {
                statement.close();
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

    /**
     * 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     */
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
