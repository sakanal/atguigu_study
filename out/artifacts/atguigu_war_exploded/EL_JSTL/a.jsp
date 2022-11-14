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
		request.setAttribute("key1", "值");
		request.setAttribute("key2", null);
	%>
	表达式脚本输出key的值是：<%=request.getAttribute("key1") %><br>
	EL表达式输出key的值是：${key1}<br>
	表达式脚本输出空值：<%=request.getAttribute("key2") %><br>
	表达式脚本输出空值(经过判断)：<%=request.getAttribute("key2")==null?"":request.getAttribute("key2") %><br>
	EL表达式输出空值：${key2}<br>
</body>
</html>