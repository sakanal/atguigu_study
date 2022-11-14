package com.sakanal.JavaWeb.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class request
 */
@WebServlet("/Request")
public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath()); 
		
//		response.setContentType("text/html;charset=UTF-8");		//解决中文为  ？  的问题
//		request.setCharacterEncoding("UTF-8");
		 
		PrintWriter out=response.getWriter();
		System.out.println("测试成功");
		
//		1、getRequestURI()					获取请求的资源路径	
		System.out.println("URI==>"+request.getRequestURI());
		
//		2、getRequestURL()					获取请求的统一资源定位符（绝对路径）		
		System.out.println("URL==>"+request.getRequestURL());
		
//		3、getRemoteHost()					获取客户端的ip地址		
		System.out.println("客户端ip地址==>"+request.getRemoteHost());
		/*
		 * 使用localhost访问时，得到的客户端ip地址是===>127.0.0.1				本机访问本机会获得Ipv6，即0:0:0:0:0:0:0:1
		 * 使用127.0.0.1访问时，得到的客户端ip地址是===>127.0.0.1
		 * 使用   真实ip  访问时，得到的客户端ip地址是===>真实的客户端ip地址
		 */
//		4、getHeader()					获取请求头				
		System.out.println("请求头User-Agent==>"+request.getHeader("User-Agent"));
		
//		5、getMethod()					获取请求的方式GET或POST
		System.out.println("Method==>"+request.getMethod());
		
		out.flush();
		out.close();
		
//		6、					获取请求的参数		
//		7、					获取请求的参数（多个值的时候使用）
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
