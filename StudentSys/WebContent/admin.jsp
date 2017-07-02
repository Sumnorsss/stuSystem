<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cql.course.vo.Course" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
<style type="text/css">
div{
	margin:0 250px;
}
</style>
</head>
<body>
	<%List<Course> list = (List<Course>)request.getAttribute("list"); %>
	<table width="600" align="center">
		<tr>
			<td>课程号</td>
			<td>课程名称</td>
			<td>授课老师</td>
			<td>操作</td>
		</tr>
		<%
		if(list != null){
			for(Course c : list){%>
				<tr>
					<td><%=c.getcId() %></td>
					<td><%=c.getcName() %></td>
					<td><%=c.getTeacher() %></td>
					<td>
						<a href="adminUpdate.jsp?cid=<%=c.getcId()%>&cname=<%=c.getcId()%>&teacher=<%=c.getTeacher()%>">修改课程</a>|
						<a href="<%=request.getContextPath()%>/student?type=asDelete&cid=<%=c.getcId()%>">删除课程</a>
					</td>
				</tr>
				
				
				
			<%}
		}
		%>
		
	
	</table>
	<div ><a href="adminAdd.jsp">添加课程</a></div>
</body>
</html>