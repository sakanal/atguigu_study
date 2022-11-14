<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//往四个域中都保存了相同的key的数据
		//当四个域中都有相同的key的数据的时候，EL表达式会按照四个域的从小到大的顺序去进行搜索，找到就输出
		//		application		>		session	>		request		>		pageContext
		pageContext.setAttribute("key", "pageContext");
		request.setAttribute("key", "request");
		//浏览器未关闭，session的值不会消失
		session.setAttribute("key", "session");
		application.setAttribute("key", "application");
	%>
	${key }
</body>
</html>