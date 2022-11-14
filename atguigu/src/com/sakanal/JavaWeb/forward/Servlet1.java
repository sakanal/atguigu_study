package com.sakanal.JavaWeb.forward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取请求参数
		PrintWriter out=response.getWriter();
		out.println("Servlet1");
		String username=request.getParameter("username");
		System.out.println("在Servlet1中查看参数："+username);
		
		//确定信号，并传递到Servlet2
		request.setAttribute("key", "1");
		
		// 获取Servlet2的路径
		/*
		 * 请求转发必须要以斜杠打头， / 斜杠表示http://ip:port/工程名/   ,映射到web目录
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Servlet2");
		
		//转到Servlet2
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
