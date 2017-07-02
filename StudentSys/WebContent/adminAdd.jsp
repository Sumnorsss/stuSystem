<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加课程</title>
<style type="text/css">
div{
	margin:5px 0;
}
</style>

</head>
<body>
	<form action="<%=request.getContextPath()%>/student?type=adminAdd" method="post">
		<div>课程编号：<input type="text" name="cid" value="7"/></div>
		<div>课程名称：<input type="text" name="cname" value="J2EE"/></div>
		<div>授课教师：<input type="text" name="teacher" value="Rice"></div>
		<div><input type="submit" name="sub" value="点击添加"></div>
		
		<input type="text" name="sid"/>
		<input type="hidden" name="type"/>
		
	</form>
	
</body>
</html>