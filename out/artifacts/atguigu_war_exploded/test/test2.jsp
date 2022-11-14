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
		String user1=request.getParameter("user1");
		String user2=(String)session.getAttribute("user2");
	%>
	<%=user1 %>
	<%=user2 %>
</body>
</html>