package com.sakanal.JDBC.jdbc_1.connection;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    //方式一
    @Test
    public void testConnection1() throws SQLException {
        //获取Driver的实现类对象
        Driver driver=new com.mysql.jdbc.Driver();

        //jdbc:mysql    协议
        //localhost     ip地址
        //3306          默认的mysql的端口号
        //test          数据库名
        String url="jdbc:mysql://localhost:3306/test";
        //将用户名和密码封装在Properties中
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");

        Connection connection = driver.connect(url, info);

        System.out.println("connection = " + connection);
    }

    //方式二：对方式一的迭代：在如下的程序中不出现第三方的api，使得程序具有更好的可移植性
    @Test
    public void testConnestion2() throws SQLException, Exception {
        //1.获取Driver的实现类对象：使用反射来实现
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver=(Driver) aClass.newInstance();

        //2.提供要连接的数据库
        String url="jdbc:mysql://localhost:3306/test";

        //3.提供连接需要的用户名和密码
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");

        //4.获取连接
        Connection connection = driver.connect(url, info);
        System.out.println("connection = " + connection);
    }

    //方式三：使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception {

        //1.获取Driver实现类对线
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //2.提供另外三个基本信息
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="123456";

        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("connection = " + connection);

    }

    //方式四：可以只是加载驱动，不用显示的注册驱动
    @Test
    public void testConnection4() throws Exception {
        //1.提供另外三个基本信息
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="123456";

        //2.加载Driver
        Class.forName("com.mysql.jdbc.Driver");
        //较于方式三，可以省略如下的操作：
//        Driver driver = (Driver) aClass.newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);
        //在Mysql的Driver实现类中，声明了注册驱动的静态方法

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("connection = " + connection);

    }

    //方式五（final版）：将数据库连接需要的四个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接
    /*
        好处
        1.实现了数据与代码之间的分离。实现了解耦（可以通过修改配置文件的方式对程序进行修改）
        2.如果需要修改配置文件信息，可以避免程序重新打包。
     */
    @Test
    public void testConnection5() throws Exception {

        //1.读取配置文件中的4个基本信息
        //InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_1/connection/jdbc.properties");

        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("connection = " + connection);
    }
}
