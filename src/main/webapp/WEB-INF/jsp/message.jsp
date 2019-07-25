<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cn.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 消息体展示页面 -->
<h1>消息体页面</h1>
	<table>
        <tr>
            <td>id</td>
            <td>title</td>
            <td>time</td>
            <td>content</td>
        </tr>
        <!-- 列出所有的信息 -->
       <c:forEach items="${list}" var="temp">
            <tr>
                <td>${temp.id }</td>
                <td>${temp.title }</td>
                <td>${temp.time }</td>
                <td>${temp.content }</td>
                <td><a href="delete.action?id=${temp.id }">删除</a>|<a href="editMessage.action?id=${temp.id }">修改</a></td>
            </tr>
       </c:forEach>

        <tr>
            <td colspan="6" style="text-align: left;"><a href="add.action">添加信息</a></td>
        </tr>
    </table>
</body>
</html>