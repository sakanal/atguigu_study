package com.sakanal.JavaWeb.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet{


	protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建和获取Seesion会话对象
		HttpSession session = req.getSession();
		//判断当前Session会话，是否是新创建出来的
		 boolean isNew=session.isNew();
		 //获取Session会话的唯一标识
		 String id=session.getId();
		 
		 resp.getWriter().write("得到的Session，它的id是："+id+"<br>");
		 resp.getWriter().write("这个Session是否是新创建的："+isNew+"<br>");
		
	}
	//往session中保存数据
	protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("key1", "value1");
	}
	//获取session域中的数据
	protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object attribute = req.getSession().getAttribute("key1");
		resp.getWriter().write("从Session中获取出key1的数据是："+attribute);
	}

	protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取session的默认超时
		int maxInactiveInterval= req.getSession().getMaxInactiveInterval();
		
		resp.getWriter().write("session的默认超时时长为："+ maxInactiveInterval+"秒");
	}
	

	protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//先获取session对象
		HttpSession session=req.getSession();
		//设置当前session	3秒后
		session.setMaxInactiveInterval(3);
		
		resp.getWriter().write("当前session已经设置为3秒后超时");
	}
	

	protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.invalidate();
		
		resp.getWriter().write("session已经设置为超时（无效）");
	}

}
