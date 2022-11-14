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
	pageContext			(PageContextImpl类)			当前jsp页面范围内有效																			由scope.jsp转到scope2.jsp，离开页面，该域对象无数据
	request					(HttpServletRequest类)		一次请求内有效																						请求转发，一次请求。故数据存在
	session					(HttpSession类)					一次会话范围内有效（打开浏览器访问服务器，直到关闭浏览器）		浏览器未关
	application				(ServletContext类)				整个web工程范围内都有效（只要web工程不停止，数据都在）			服务器未停
	
		域对象是可以像Map一样存取数据的对象。四个域对象功能一样。不同的是它们对数据的存取范围。
 --%>
 <%--
 	虽然四个域对象都可以存取数据，在使用上它们是有优先顺序的。
 	四个域在使用的时候，优先顺序分别是，它们从小到大的范围的顺序。
 		pageContext	===>	request	===>	session	===>	application
 	优化内存
  --%>
	<h1>scope2.jsp页面</h1>
	pageContext域是否有值：<%=pageContext.getAttribute("key") %><br>
	request域是否有值：<%=request.getAttribute("key") %><br>
	session域是否有值：<%=session.getAttribute("key") %><br>
	application域是否有值：<%=application.getAttribute("key") %><br>
</body>
</html>