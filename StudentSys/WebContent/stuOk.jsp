<%@page import="com.cql.course.vo.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生选课界面</title>
</head>
<body>

	<h1>欢迎您：<%=session.getAttribute("username") %></h1>
	<%List<Course> list = (List<Course>) request.getAttribute("list");%>
	<form action="">
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
					<td><%=c.getTeacher()%></td>
					<td><a href="<%=request.getContextPath()%>/student?type=selectC&cid=<%=c.getcId()%>">选课</a></td>
			</tr>
						
					<%}
				}
				%>
			
		</table>
		
			<a href="<%=request.getContextPath()%>/student?type=selectedShow">查看所选课程</a>
	</form>
</body>
</html>