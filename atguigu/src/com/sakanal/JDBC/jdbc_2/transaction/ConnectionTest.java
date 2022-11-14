package com.sakanal.JDBC.jdbc_2.transaction;

import com.sakanal.JDBC.jdbc_2.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTest {
    @Test
    public void testGetConnection() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);

    }
}
