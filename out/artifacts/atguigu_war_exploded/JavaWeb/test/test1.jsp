<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int sum=(int)request.getSession().getAttribute("sum");
	%>
	<form action="/atguigu/JavaWeb/test" method="get">
		<input type="text" name="a">
		<input type="text" name="b">
		<input type="submit">
	</form>
	<%=sum %>
	<table>
	<%
		for(int i=1;i<10;i++){
			out.print("<tr>");
			for(int j=1;j<=i;j++){
				out.print("<td style='border:1px solid;'>"+i+"*"+j+"="+i*j+"</td>");
			}
			out.print("<br>");
			out.print("</tr>");
		}
	%>
	</table>	
	<br>
	<table>
	<%
		for(int i=9;i>0;i--){
			out.print("<tr>");
			for(int j=i;j>0;j--){
				out.print("<td style='border:1px solid;'>"+i+"*"+j+"="+i*j+"</td>");
			}
			out.print("<br>");
			out.print("</tr>");
		}
	%>
	</table>
</body>
</html>