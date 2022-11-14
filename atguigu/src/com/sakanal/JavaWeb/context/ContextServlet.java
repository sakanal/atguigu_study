package com.sakanal.JavaWeb.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContextServlet
 */
@WebServlet("/ContextServlet")
public class ContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		1、获取 web.xml 中配置的上下文参数 context-param
		ServletContext context = getServletConfig().getServletContext();
		
		String username=context.getInitParameter("username");
		System.out.println("context-param 参数 username 的值是:" + username);
		System.out.println("context-param 参数 password 的值是:" + context.getInitParameter("password"));
		
//		2、获取当前的工程路径，格式: /工程路径
		System.out.println( "当前工程路径:" + context.getContextPath() );
		
//		3、获取工程部署后在服务器硬盘上的绝对路径
		/*
		 *    / 斜杠被服务器解析地址为:http://ip:port/工程名/ 映射到 IDEA 代码的 web 目录<br/>
		 */
		System.out.println("工程部署的路径是:" + context.getRealPath("/")); 
		System.out.println("工程下 css 目录的绝对路径是:" + context.getRealPath("/css"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
