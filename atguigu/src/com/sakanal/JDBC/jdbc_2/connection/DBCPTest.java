package com.sakanal.JDBC.jdbc_2.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBCPTest {
    /**
     *  测试DBCP数据库连接池技术
     */
    //方式一：不推荐
    @Test
    public void testGetConnection() throws Exception {
        //创建了DBCP的数据库连接池
        BasicDataSource source=new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("123456");

        //还可以设置其他涉及数据库连接池管理的相关属性
        source.setInitialSize(10);
        source.setMaxActive(10);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    //方式二：使用配置文件
    @Test
    public void testGetConnection1() throws Exception {
        Properties properties = new Properties();
        //方式1：
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("DBCP.properties");
        //方式2：
        FileInputStream is = new FileInputStream(new File("src/com/sakanal/JDBC/jdbc_2/connection/util/dbcp.properties"));
        properties.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(properties);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
