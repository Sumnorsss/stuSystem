<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language = "javascript" src = "code.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- http://210.43.247.49/style/standard/images/login_right.jpg-->
<title>欢迎使用正方管理系统</title>
<style type=text/css>
body {
	width: 462px;
	height: 340;
	background:
		url(http://210.43.247.43/style/standard/images/login_right.jpg)
		no-repeat;
}
/*http://blog.jobbole.com/49320/*/

/*大的div*/
#center {
	position: absolute;
	width: 400px;
	height: 360px;
	margin: 0px;
}

/*左边的div*/
#left {
	width: 30px;
	height: 121px;
	position: absolute;
	top: 8px;
	left: 20;
	margin-top: 100px;
	margin-left: 80px;
	margin-bottom: 0px;
	margin-right: px;
	float: left;
}

/*右边的div*/
#right {
	width: 300px;
	height: 120px;
	position: relative;
	left: 10px;
	top: 20px;
	margin-top: 80px;
	margin-left: 100px;
	margin-bottom: 10px;
	/*margin-right: 10px;*/
	float: right;
	margin-left: 100px;
	margin-bottom: 10px;
	margin-bottom: 10px;
}

#YZ {
	width: 330px;
	height: 240px;
}

/*下面的div*/
#bottom {
	positon: absolute;
	top: 80px;
	left: 60px;
	width: 320px;
	height: 60px;
	margin-top: 15px;
	float: right;
}

table {
	width: 200;
	height: 200;
}

.headline {
	font-size: 10px;
	text-align: left;
	/*font-family:#0099FF;*/
}

.imgfloat {
	float: left;
}

.button {
	/*登录按钮样式*/
	background-color: #0099ff;
	background: url(http://210.43.247.43/style/standard/images/login_in.gif);
}
.button2{
	background-color: #0099ff;
	background:url(http://210.43.247.44/style/standard/images/login_res.gif);
}

.code {
	font-family: Arial;
	font-style: italic;
	color: blue;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
}
</style>
</head>
<body >
	<form  action="<%=request.getContextPath()%>/student?type=loginOk" method=post  onload = "createCode();">
		<div id="center">

			<div id="left">
				<!-- http://210.43.247.43/style/standard/images/login_ico2.gif -->
				<img src="http://210.43.247.43/style/standard/images/login_ico2.gif" />
			</div>

			<div style="color: #0099FF" id="right">
				<table border="0">
					<tr>
						<td class="headline"><label for= "1" >用户名：</label><input required = "required" type="text" name="username" id = "1"  /><br />
							<br /> 密&nbsp&nbsp码 ：<input required = "required" type="password" name="password" /><br />
							 
							<br /> 验证码：<input type="text" name="checknum"
							style="width: 76px; height: 18px" id="input1" /> 
							<input type="text" class="code" id="checkCode" readonly style="width: 55px" onclick = "createCode();"/> 
							<a
							href="#" onclick="createCode();">看不清，换一张</a><br />
							<input type="radio" name="logType" value="student" checked/>学生 
							<input type="radio" name="logType" value="admin">管理员 <br /> <br />
							
						</td>
					</tr>
				</table>
			</div>
			<!-- url(http://210.43.247.48/style/standard/images/login_in.gif) -->
			<div style="color: #0099FF" id="bottom" class="headline">

					
					<input  id = "Button1" type="submit" class="button" name="Sub" value="　　　&nbsp"
					onclick="return validate();"/> 
				<input type="reset" class="button2"name="rSet" value="　　　&nbsp" />
				<a href="register.jsp">没有账号，去注册</a>
				<input type="hidden"  name="type"  />
			</div>
		</div>
	</form>


</body>
</html>
