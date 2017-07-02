<%@page import="java.util.List"%>
<%@page import="com.cql.course.vo.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所选课程</title>
</head>
<body>
	<%List<Course> list = (List<Course>)request.getAttribute("list");%>
	
	session: ${sessionScope.list }
	<form action="" method="post">
		<table width="600">
			<tr>
				<td>课程编号</td>
				<td>课程名称</td>
				<td>授课老师</td>
				<td>操作</td>
			</tr>
			
			<%
			if(list != null){
				System.out.print("test list: "+list);
				for(Course c:list){
				System.out.println(c.getcName());
				
				%>
				<tr>
					<td><%=c.getcId() %></td>
					<td><%=c.getcName() %></td>
					<td><%=c.getTeacher() %></td>
					<td><a href="<%=request.getContextPath()%>/student?type=loginShow&cid=<%=c.getcId()%>">继续选课</a>
					|	<a href="<%=request.getContextPath()%>/student?type=delete&cid=<%=c.getcId()%>">退选</a>
					</td>
				
				</tr>
					
				<%}
			}
			
			%>
			
		</table>
	
	</form>
</body>
</html>