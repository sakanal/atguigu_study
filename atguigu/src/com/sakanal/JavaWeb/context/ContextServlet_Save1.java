package com.sakanal.JavaWeb.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContextServlet_Save
 */
@WebServlet("/ContextServlet_Save1")
public class ContextServlet_Save1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextServlet_Save1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取 ServletContext 对象
		ServletContext Context=getServletContext();
		if(Context.getAttribute("key1")==null) {
			System.out.println("对象为空");
		}
		else {
			System.out.println("对象不为空");
		}
		System.out.println("ContextServlet_Save1 中获取域数据 key1 的值是:"+Context.getAttribute("key1"));
		
		Context.setAttribute("key1"," value1");
		System.out.println("重新设置key1的值");
		System.out.println("ContextServlet_Save1 中获取域数据 key1 的值是:"+Context.getAttribute("key1"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
