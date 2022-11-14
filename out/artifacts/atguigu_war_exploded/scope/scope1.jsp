<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	pageContext			(PageContextImpl类)			当前jsp页面范围内有效
	request					(HttpServletRequest类)		一次请求内有效
	session					(HttpSession类)					一次会话范围内有效（打开浏览器访问服务器，直到关闭浏览器）
	application				(ServletContext类)				整个web工程范围内都有效（只要web工程不停止，数据都在）
 --%>
	<h1>scope.jsp页面</h1>
	
	<%
		//往四个域中都分别保存了数据
		pageContext.setAttribute("key", "pageContext");
		request.setAttribute("key", "request");
		session.setAttribute("key", "session");
		application.setAttribute("key", "application");
	%>
	
	pageContext域是否有值：<%=pageContext.getAttribute("key") %><br>
	request域是否有值：<%=request.getAttribute("key") %><br>
	session域是否有值：<%=session.getAttribute("key") %><br>
	application域是否有值：<%=application.getAttribute("key") %><br>
	
	<%
//		request.getRequestDispatcher("/scope/scope2.jsp").forward(request, response);
	%>
	
	<%--
		<jsp:forward page=""></jsp:forward>是请求转发标签，它的功能就是请求转发
			page	属性设置请求转发的路径
			
	和上面的那个一样
	 --%>
	 <jsp:forward page="/scope/scope2.jsp"></jsp:forward>
</body>
</html>