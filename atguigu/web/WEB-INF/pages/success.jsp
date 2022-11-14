<%--
  Created by IntelliJ IDEA.
  User: sakanal
  Date: 2022/3/16
  Time: 7:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>成功页面</title>
    </head>
    <body>
        <table>
            <tr>
                <td>id</td>
                <td>name</td>
                <td>password</td>
                <td>gender</td>
                <td>address</td>
                <td>phone</td>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.password}</td>
                    <td>${user.gender}</td>
                    <td>${user.address}</td>
                    <td>${user.phone}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
