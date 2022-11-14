package com.sakanal.JavaWeb.cookie;

import com.sakanal.JavaWeb.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet{

	protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、创建Cookie对象
		Cookie cookie=new Cookie("key1","value1");
		//2、通知客户端保存Cookie
		response.addCookie(cookie);
		cookie=new Cookie("key2","value2");
		//2、通知客户端保存Cookie
		response.addCookie(cookie);
		response.getWriter().write("Cookie 创建成功！");
	}
	protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies= request.getCookies();
		Cookie iWantCookie=null;
		
		for(Cookie cookie:cookies) {
			//getName方法返回Cookie的key（名称）
			//getValue方法返回Cookie的value值
			response.getWriter().write("Cookie["+cookie.getName()+"="+cookie.getValue()+"]<br>");
		}
		
		for(Cookie cookie:cookies) {
			if("key2".equals(cookie.getName())){
				iWantCookie=cookie;
				break;
			}
		}
		//如果不等于null，说明找到了需要的cookie
		if(iWantCookie !=null) {
			response.getWriter().write("找到的需要的cookie\n");
			response.getWriter().write("Cookie["+iWantCookie.getName()+"="+iWantCookie.getValue()+"]<br>");
		}
	}
	

	protected void updataCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//方案一：
		//1、先创建一个要修改的同名的Cookie对象
		//2、在构造器，同时赋予新的Cookie值
//		Cookie cookie=new Cookie("key1", "newValue1");
		//3、调用response.addCookie（Cookie）	通知	客户端	保存修改
//		response.addCookie(cookie);
		
		//方案二
		//1、先查找找到需要修改的Cookie对象
		Cookie cookie=CookieUtils.findCookie("key1", request.getCookies());
		if(cookie!=null) {
			//调用setValue()方法赋予新的Cookie值
			cookie.setValue("newnewValue1");
			//3、调用response.addCookie（Cookie）	通知	客户端	保存修改
			response.addCookie(cookie);
		}
		response.getWriter().write("key1的Cookie值已经修改");
	}
	
	

	protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie=new Cookie("defaultLife","defaultLife");
		cookie.setMaxAge(-1);					//session级别，浏览器关闭则删除cookie
		response.addCookie(cookie);
	}

	protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先找到你要删除的cookie对象
		Cookie cookie =CookieUtils.findCookie("key2", request.getCookies());
		if(cookie!=null) {
			//调用setMaxAge()
			cookie.setMaxAge(0);			//表示马上删除，不需要等待浏览器关闭
			System.out.println(cookie.getValue());
			//调用response.addCookie(cookie)
			response.addCookie(cookie);
			
			response.getWriter().write("key2的cookie已经被删除");
		}
	
	}
	protected void life3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie=new Cookie("life3600", "life3600");
		cookie.setMaxAge(60*60);				//设置Cookie一小时之后被删除。无效
		response.addCookie(cookie);
		response.getWriter().write("已经创建了一个存活一小时的Cookie");
	}
	

	protected void testPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie=new Cookie("path","path1");
		//getContextPath()===>>得到工程路径
		cookie.setPath(request.getContextPath()+"/abc");				http://ip:port//工程路径/abc/.....	之后的才可以查看到这个cookie，无论网页是否可以打开
		response.addCookie(cookie);
		response.getWriter().write("创建了一个带有Path路径的Cookie");
	}
}