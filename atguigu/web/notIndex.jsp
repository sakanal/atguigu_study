<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@page import="test.student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String basePath=request.getScheme()			//http
	+"://"
	+request.getServerName()								//localhost或192.168.2.10
	+":"
	+request.getServerPort()									//8080
	+request.getContextPath()								//工程路径
	+"/";
%>

<style type="text/css">
	table{
		border: 1px red solid;
	}
	td,th{
		border: 1px red solid;
	}
	td{
		width:70px;
		text-align: center;
	}
</style>
</head>
<body>
<%--
	<% %>JSP脚本，在JSP页面中执行的java代码
	<%! %>JSP声明，在JSP页面中定义变量或者方法。
	<%= %>JSP表达式，在JSP页面中执行的表达式。
--%>
<%!
	//jsp声明,定义变量和方法
	String s="张三";
	int add(int a,int b){
		return a+b;
	}
%>
<%
	s="李四";
	out.println(s);
	out.println("<"+"%&nbsp&nbsp&nbsp&nbsp%"+">是jsp脚本");
	out.println("jsp尝试");
	out.println("println不会产生回车");
%><br>
Helloworld<br>
<%out.println("<"+"%=%"+">在jsp页面中执行表达式"); %><br>
我是<%=s%><br>
x=8,y=9<br>
x+y=<%=add(8,9)%><br><br>
<%
	out.println("九九乘法表：用jsp脚本方式实行<br>");
	for(int i=1;i<=9;i++){
		for(int j=1;j<=i;j++){
			out.println(i+"*"+j+"="+i*j+"&nbsp&nbsp&nbsp&nbsp&nbsp");
		}
		out.println("<br>");
	}
%><br><br>
<%--
	jsp脚本关于九九乘法表的函数实行
	void printMultiTable(JspWriter out)throws Exception{
		for(int i=1;i<=9;i++){
			for(int j=1;j<=i;j++){
				out.println(i+"*"+j+"="+i*j+"&nbsp&nbsp&nbsp&nbsp&nbsp");
			}
			out.println("<br>");
		}
	}
	
	
	ptintMultiTable(out);
 --%>
<%!
	String printMultiTable(){
		String sum="";
		for(int i=1;i<=9;i++){
			for(int j=1;j<=i;j++){
				sum+=i+"*"+j+"="+i*j+"&nbsp&nbsp&nbsp&nbsp&nbsp";
			}
			sum+="<br>";
		}
		return sum;
	}
%>
九九乘法表：用JSP声明和表达式方式实行<br>
<%=printMultiTable() %>
<%out.println("是否可以热部署tomcat");%>
<%out.println("可以"); %>
<%out.println("对于jsp脚本似乎会快速显示"); %>
而对于html内容似乎不会<br>
看来还是一样的
<table>
	<%
		for(int i=1;i<10;i++){
			out.print("<tr>"+"<td>"+i+"号"+"</td>"+"<td>"+"姓名"+"</td>");
			out.print("</tr>");
		}
	%>
</table>


	<%
		@SuppressWarnings("unchecked")
		List<student> studentList=(List<student>) request.getAttribute("stuList");
	%>
	<table>
		<%	for(student student:studentList){ %>
			<tr>
				<td><%=student.getId() %></td>
				<td><%=student.getName() %></td>
				<td><%=student.getAge() %></td>
				<td><%=student.getPhone() %></td>
			</tr>
		<%} %>
	</table>
</body>
</html>