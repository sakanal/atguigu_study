package com.sakanal.JDBC.jdbc_2.connection;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    @Test
    public void getConnection() throws Exception {
        Properties properties = new Properties();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_2/connection/util/druid.properties");

        properties.load(is);

        DataSource source = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
