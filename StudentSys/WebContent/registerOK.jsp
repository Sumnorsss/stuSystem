<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.cql.course.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body>
	<% Student stu =(Student)request.getAttribute("stu"); %>

	注册成功，您的信息如下
	用户名：<%=stu.getSid()%><br/>
	密　码：******<br/>
	姓　名：<%=stu.getSname() %><br/>
	班　级：<%=stu.getSclass() %><br/>
	学　院：<%=stu.getAcdemy()%><br/>
	
	<font color="blue">3秒后进入登录界面</font>
	
	<%response.setHeader("Refresh", "3;url=login.jsp"); %>
	
</body>
</html>