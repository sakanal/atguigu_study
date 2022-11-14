package com.sakanal.JavaWeb.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Servlet implementation class Parameter
 */
@WebServlet("/Parameter")
public class Parameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parameter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");			//解决中文为  ？  的问题
		request.setCharacterEncoding("UTF-8");		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
//		String hobby=request.getParameter("hobby");
		String[] hobby=request.getParameterValues("hobby");
		
		System.out.println("用户名："+username);
		System.out.println("密码："+password);
		System.out.println("兴趣爱好："+Arrays.toString(hobby));
		
		PrintWriter out=response.getWriter();
		out.print("用户名："+username);
		out.print("密码："+password);
		out.print("兴趣爱好："+Arrays.asList(hobby));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
