package com.sakanal.JavaWeb.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
//import javax.servlet.http.HttpServletResponse;

public class HelloServlet implements Servlet{

	public HelloServlet() {
		System.out.println("helloServlet：构造器方法");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("getServletConfig");
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("getServletInfo");
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init初始化方法");
		
		//1. 可以获得servlet程序的别名servlet-name的值
		System.out.println("HelloServlet程序的别名是："+config.getServletName());
		//2. 获取初始化参数init-param
		System.out.println("初始化参数username的值是："+config.getInitParameter("username"));
		System.out.println("初始化参数url的值是："+config.getInitParameter("url"));
		//3. 获取servletcontext对象
		System.out.println(config.getServletContext());
		
	}

	/**
	 * service方法是专门用来处理请求和响应的
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service hello servlet被访问了！");
		
		HttpServletRequest HttpServletRequest=(HttpServletRequest) req;
		String method=HttpServletRequest.getMethod();

//		HttpServletResponse HttpServletResponse=(HttpServletResponse) res;
//     PrintWriter out = HttpServletResponse.getWriter();
        
		if("GET".equals(method)) {
//			System.out.println("GET请求");
//			out.print("GET请求");
			doGet();
		}else if("POST".equals(method)){
//			System.out.println("POST请求");
//			out.print("POST请求");
			doPost();
		}
		
//		out.flush();
//		out.close();
		
	}
	
	public void doGet() {
		System.out.println(Charset.defaultCharset());
		System.out.println("GET请求");
	}
	public void doPost() {
		System.out.println("POST请求");
	}
}
