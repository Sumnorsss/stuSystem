<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style type="text/css">
	body {
	width: 462px;
	height: 340;
	background:
		url(http://210.43.247.43/style/standard/images/login_right.jpg)
		no-repeat;
	}
	
	div{
		margin:5px 5px;
	}
	
	.text{
		margin:90px 50px;
	
	}

</style>

</head>
<body>
	<form action="<%=request.getContextPath()%>/student?type=register" method="post">
		<div class="text">
	
			<div>学　号：<input type="text" name="stuId" required="required"/></div>
			
			<div>密　码：<input type="password" name="password" required="required"/></div>
			
			<div>姓　名：<input type="text" name="stuName" required="required"/></div>
			
			<div>班　级：<input type="text" name="stuClass" required="required"/></div>
			
			<div>学　院：<input type="text" name="acamedy" required="required"/></div>
			
			<input type="submit" name="sub"value="注册"/>
			<input type="hidden"  name="type"/>
			
		</div>
		
	
	</form>
	
	
</body>
</html>