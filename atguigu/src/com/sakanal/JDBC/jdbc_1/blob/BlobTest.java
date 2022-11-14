package com.sakanal.JDBC.jdbc_1.blob;

import com.sakanal.JDBC.jdbc_1.bean.Customer;
import com.sakanal.JDBC.jdbc_1.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

/**
 * 测试使用PreparedStatement操作Blob类型的数据
 */
public class BlobTest {

    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql="select id,name,email,birth,photo from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,20);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                //方式一
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            String email = resultSet.getString(3);
//            Date birth = resultSet.getDate(4);

                //方式二
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                //将Blob类型的字段下载下来，以文件的形式保存在本地
                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                fileOutputStream = new FileOutputStream(new File("D://Utaha.jpg"));
                byte[] buffer = new byte[1024];
                int len;
                while ((len=is.read(buffer))!=-1){
                    fileOutputStream.write(buffer,0,len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream!=null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
    }

    //向数据表customers中插入blob类型的字段
    @Test
    public void testInsert() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql="insert into customers(name,email,birth,photo)value (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,"诗羽");
        preparedStatement.setObject(2,"Utaha@gmail.com");
        preparedStatement.setObject(3,"1992-09-08");
        FileInputStream is = new FileInputStream(new File("images/Utaha.jpg"));
        preparedStatement.setBlob(4,is);

        preparedStatement.execute();
    }

}
