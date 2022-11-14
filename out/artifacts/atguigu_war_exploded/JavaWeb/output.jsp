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
		out.write("out输出1<br>");
		out.write("out输出2<br>");
		response.getWriter().write("response输出1<br>");
		response.getWriter().write("response输出2<br>");
	%>
	<%--
		当jsp页面中所有代码执行完成后会做以下两个操作
			1、执行out.flush()操作，会把out缓冲区中的数据追加写入到response缓冲区mo'wei
			2、执行response的刷新操作，把全部数据写给客户端
		由于jsp翻译后，底层源代码都是使用out来进行输出
		所以一般情况下统一使用out进行输出。避免打乱页面输出内容的顺序
	 --%>
	 <%
		out.write(12);
		out.print(13);
	 %>
	 <%--
	 	out.write()输出字符串没有问题，输出整型会因为在源代码中，通过（char）int 转化成Ascii码进入缓冲区而chu'x'w't
		out.print()输出任意数据都没问题（都转换成为字符串后调用write输出）
	  --%>
</body>
</html>