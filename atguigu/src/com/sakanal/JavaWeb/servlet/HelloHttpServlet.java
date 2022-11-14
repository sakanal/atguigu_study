package com.sakanal.JavaWeb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloHttpServlet extends HttpServlet {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//super.init()需要保留，在父类中的init()在进行保存config的工作，重写init会覆盖方法，因此需要使用super运行父类的init方法，否则会出现问题：空指针
		System.out.println("重写init()方法，有一些指令执行");
		}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		System.out.println("get请求");
		
		//
		ServletConfig servletConfig=getServletConfig();
		System.out.println(servletConfig);
		System.out.println("初始化参数username的值是："+servletConfig.getInitParameter("username"));
		System.out.println("初始化参数url的值是："+servletConfig.getInitParameter("url"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		System.out.println("post请求");
	}

}
