<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员修改课程</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/student?type=adminUpdate" method="post">
		<div>课程编号：<input type="text" name="cId" value="<%=request.getParameter("cid")%>"/></div>
		<div>课程名称：<input type="text" name="cName" value="<%=request.getParameter("cname")%>"/></div>
		<div>授课教师：<input type="text" name="cTeacher" value="<%=request.getParameter("teacher")%>"/></div>
		<div><input type="submit" name="sub" value="点击修改"></div>
		
		<input type="hidden" name="type"/>
		
	</form>
</body>
</html>