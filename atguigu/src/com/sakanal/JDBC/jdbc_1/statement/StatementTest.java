package com.sakanal.JDBC.jdbc_1.statement;


import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class StatementTest {

	// 使用Statement的弊端：需要拼写sql语句，并且存在SQL注入的问题
	//如何避免出现sql注入：只要用 PreparedStatement(从Statement扩展而来) 取代 Statement
	@Test
	public static void testLogin() {
		Scanner scanner = new Scanner(System.in);
		//.next()		空格表示输入完成
		//.nextLine()	回车表示输入完成

		System.out.println("请输入用户名:");
		String user=scanner.nextLine();
		System.out.println("请输入密码:");
		String password=scanner.nextLine();

		//sql注入：
		//select user,password from user_table where user='1' or ' and password='=1 or '1'='1'
		//在输入用户名时输入单引号来使得单引号后面的数据作为结构数据（ 空格or空格 ）
		//在输入密码时先输入=号并随便输入数据，将原判断条件取消
		//在输入（ 空格or空格 ）之后输入绝对正确语句eg：1=1
		//最后输入'1'='1
		String sql="select user,password from user_table where user='"+user+"' and password='"+password+"'";
		User returnUser = get(sql, User.class);

		if (returnUser!=null){
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
	}

	// 使用Statement实现对数据表的查询操作
	public static <T> T get(String sql, Class<T> clazz) {
		T t = null;

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1.加载配置文件
			InputStream is = StatementTest.class.getClassLoader().getResourceAsStream("com/sakanal/JDBC/jdbc_1/statement/jdbc.properties");
			Properties pros = new Properties();
			pros.load(is);

			// 2.读取配置信息
			String user = pros.getProperty("user");
			String password = pros.getProperty("password");
			String url = pros.getProperty("url");
			String driverClass = pros.getProperty("driverClass");

			// 3.加载驱动
			Class.forName(driverClass);

			// 4.获取连接
			conn = DriverManager.getConnection(url, user, password);

			st = conn.createStatement();

			rs = st.executeQuery(sql);

			// 获取结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();

			// 获取结果集的列数
			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {

				t = clazz.newInstance();

				for (int i = 0; i < columnCount; i++) {
					// //1. 获取列的名称
					// String columnName = rsmd.getColumnName(i+1);

					// 1. 获取列的别名
					String columnName = rsmd.getColumnLabel(i + 1);

					// 2. 根据列名获取对应数据表中的数据
					Object columnVal = rs.getObject(columnName);

					// 3. 将数据表中得到的数据，封装进对象
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columnVal);
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		testLogin();
	}
}
