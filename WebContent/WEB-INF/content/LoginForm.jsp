<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>系统登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/style.css">
</head>

<body oncontextmenu="return false">

	<div class="page-container">
		<h1>公文管理流系统</h1>
		<form id="loginForm" action="login" method="post">
			<div>
				<input type="text" id="username" name="username" class="username"
					placeholder="用户名" required="required" autocomplete="off" />
			</div>
			<div>
				<input type="password" id="password" name="password" class="password"
					placeholder="密码" required="required" oncontextmenu="return false"
					onpaste="return false" />
			</div>
			<button id="submit" type="submit">登录</button>
		</form>
		<div class="connect">

			<p style="margin-top: 20px;">欢迎使用公文管理流系统</p>
		</div>
	</div>
	<div class="alert" style="display: none">
		<h2>消息</h2>
		<div class="alert_con">
			<p id="ts"></p>
			<p style="line-height: 70px">
				<a class="btn">确定</a>
			</p>
		</div>
	</div>

	<!-- Javascript -->
	<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
		type="text/javascript"></script>
	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<script src="js/global.js"></script>
	<script src="js/loginForm.js"></script>
</body>

</html>