package com.sakanal.JavaWeb.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Response
 */
@WebServlet("/Response")
public class Response extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * 字节流			getOutputStream();					常用于下载（传递二进制数据）
		 * 字符流			getWriter();									常用于回传字符串（常用）
		 * 两个流同时只能使用一个
		 * 使用了字节流，就不能再使用字符流，反之亦然，否则就会报错
		 */
		
//		System.out.println(response.getCharacterEncoding());		//默认是ISO-8859-1
		
		//设置服务器字符集为UTF-8
//		response.setCharacterEncoding("UTF-8");			// 浏览器默认字符集不为UTF-8，因此中文为乱码
		//通过响应头，设置浏览器也使用UTF-8字符集
//		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		//它会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
		//此方法一定要在获取流对象之前调用才有效
		response.setContentType("text/html; charset=UTF-8");
		
//		PrintWriter out=response.getWriter();
		System.out.print("response's content!!!");
//		System.out.write("response's content!!!");
		System.out.print("中文");
//		System.out.write("中文");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
