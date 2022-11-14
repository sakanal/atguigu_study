package com.sakanal.JDBC.jdbc_2.dbutils;

import com.sakanal.JDBC.jdbc_2.bean.Customer;
import com.sakanal.JDBC.jdbc_2.connection.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * commons-dbutils 是Apache组织提供的一个开源JDBC工具类库，封装了针对于数据库的增删改查操作
 */
public class QueryRunnerTest {

    //测试插入
    @Test
    public void testInsert(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql="insert into customers(name,email,birth)values(?,?,?)";
            queryRunner.update(connection,sql,"蔡徐坤","cai@gmail.com","1990-10-05");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //测试查询

    /**
     * BeanHandler:是ResultSetHandler接口的实现类，用于封装表中的一条记录
     * @throws Exception
     */
    @Test
    public void testQuery1(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id=?";

            BeanHandler<Customer> customerBeanHandler = new BeanHandler<>(Customer.class);

            Customer customer = queryRunner.query(connection, sql, customerBeanHandler, 20);

            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    /**
     * BeanListHandler:是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合
     * @throws Exception
     */
    @Test
    public void testQuery2(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id<?";
            BeanListHandler<Customer> customerBeanListHandler = new BeanListHandler<>(Customer.class);
            List<Customer> list = queryRunner.query(connection, sql, customerBeanListHandler, 20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }
    }

    /*
     * MapHander:是ResultSetHandler接口的实现类，对应表中的一条记录。
     * 将字段及相应字段的值作为map中的key和value
     */
    @Test
    public void testQuery3(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 23);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, null);
        }

    }

    /*
     * MapListHander:是ResultSetHandler接口的实现类，对应表中的多条记录。
     * 将字段及相应字段的值作为map中的key和value。将这些map添加到List中
     */
    @Test
    public void testQuery4(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";

            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 23);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
     * ScalarHandler:用于查询特殊值
     */
    @Test
    public void testQuery5(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select count(*) from customers";

            ScalarHandler handler = new ScalarHandler();

            Long count = (Long) runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, null);

        }

    }
    @Test
    public void testQuery6(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select max(birth) from customers";

            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(conn, sql, handler);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, null);
        }
    }


    /*
     * 自定义ResultSetHandler的实现类
     */
    @Test
    public void testQuery7(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select id,name,email,birth from customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>(){

                @Override
                public Customer handle(ResultSet rs) throws SQLException {
//					System.out.println("handle");
//					return null;

//					return new Customer(12, "成龙", "Jacky@126.com", new Date(234324234324L));

                    if(rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");
                        Customer customer = new Customer(id, name, email, birth);
                        return customer;
                    }
                    return null;
                }
            };
            Customer customer = runner.query(conn, sql, handler,23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, null);
        }
    }
}
