<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	头部信息<br>
	主体信息<br>
	
	<%--
		<%@include file="" %>	就是静态包含
			file	属性指定你要包含的jsp页面的路径
			地址中第一个斜杆	/	表示未http://ip:port/工程路径/		映射到代码的web目录
			
		静态包含的特点：
			1、静态包含不会翻译被包含的jsp页面。
			2、静态包含其实是把被包含的jsp页面的代码拷贝到包含的位置执行输出
	
	
	 <%@include file="/include/footer.jsp" %> 
	 --%>
	 
	 
	 
	 
	 <%--
	  	<jsp:include page=""></jsp:include>			这是动态包含
	  		page属性是指定你要包含的jsp页面的路径
	  		动态包含也可以像静态包含一样。把被包含的内容执行输出到包含位置
	  		
	  	动态包含的特点：
	  		1、动态包含会把包含的jsp页面也翻译成为java代码
	  		2、动态包含底层代码使用如下代码去调用被包含的jsp页面执行输出。
	  			JspRuntimeLibrary.include(request,response,"/include/footer.jsp",out,false)
	  			使用main的out缓冲区，通过代码将request，response，out传递给footer
	  		3、动态包含，还可以传递参数
	  --%>
	  <jsp:include page="/include/footer.jsp">
	  	<jsp:param value="root" name="username"/>
	  	<jsp:param value="123456" name="password"/>
	  </jsp:include>
</body>
</html>