<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String nameStr = request.getParameter("name");//用request得到
    request.setAttribute("nameAttr", nameStr);
    if(2>1){
    	if(3>2){
    		int i=9;
    	}
    }
%> 
<!DOCTYPE html>   
<html>   
<head>   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <title>Hello</title>   
    <script type="text/javascript">
    	function Blur(){
    		alert("失去焦点")
    	}
    	function  blur(){
    		alert("失去焦点")
    	}
    </script>
</head>   
<body>
    Hi,<%=nameStr%><br>
    Hi,${param.name}<br>
    

     	<input type="text" onblur="Blur()"><br>
		<input type="text" onblur="blur()"><br>
		
		

		<c:if  test="${12>10}" var="result" scope="page" >
			true<br>
			12大于10	 test的结果为${result }
		</c:if> 
		<br>
		<c:if  test="${12<10 }">
			12小于10 
		</c:if>
		<br>
<c:set var="salary" scope="session" value="${2000*2}"/>
<c:if test="${salary > 2000}">
   <p>我的工资为: <c:out value="${salary}"/><p>
</c:if><br>
</body>   
</html>