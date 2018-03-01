<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/signIn.css">
	<link rel="stylesheet" href="css/font-awesome.css">
  </head>
  
  <body>
 	<div class="toper"></div>
	<script>
		$(document).ready(function() {
			bodyContent = $.ajax({
				url : "<%=path%>/top.jsp",
				global : false,
				type : "POST",
				dataType : "html",
				async : false,
				success : function(msg) {
					$(".toper").append(msg);
				}
			})
		});
	</script>
	
	<!-- 注册 -->
	<div class="jumbotron signInBg">
		<div class="col-sm-4 col-sm-offset-4 signIn-form-div">
			<div class="text-center signInTitle">
				<p>用户注册</p>
			</div>
			<form class="signIn-form col-md-10 col-md-offset-1" action="<c:url value='/UserServlet?method=regist'/>" method="post">
				<div class="form-group">
					<label for="signIn_username">姓名：</label>
					<input type="text" class="form-control" id="signIn_username" placeholder="请输入姓名" name="username" required="required">
				</div>
				<div class="form-group">
					<label for="signIn_shen">身份：</label>
					<label class="radio-inline">
  					<input type="radio" name="signIn_shen" id="signIn_shen" value="老师">老师
					</label>
					<label class="radio-inline">
 					 <input type="radio" name="signIn_shen" id="signIn_shen" value="学生">学生
					</label>
				</div>
				<div class="form-group">
					<label for="signIn_email">邮箱：</label>
					<input type="email" class="form-control" id="signIn_email" placeholder="请输入邮箱" name="email" required="required">
				</div>
				<div class="form-group">
					<label for="signIn_password">密码：</label>
					<input type="password" class="form-control" id="signIn_password" placeholder="请输入密码" name="password" required="required">
				</div>
				<div class="form-group" >
					<label for="signIn_confirm_password">确认密码：</label>
					<input type="password" class="form-control" id="signIn_confirm_password" placeholder="请确认密码" name="r_password" required="required" onBlur="Check()"/>
					<div id="msg_parent">
						<span id="msg"></span>
					</div>	
				</div>
				<div class="checkbox">
					<a href="index.jsp" data-toggle="modal" data-target=".bs-example-modal-sm">有账号了？直接登录</a>
				</div>
				<button type="submit" class="btn btn-primary btn-block">确认</button>
			</form>
		</div>
	</div>
	<!-- 注册 -->
	<!-- 底部 -->
	<div class="footer">
		<span>&copy;观光组合</span>
	</div>
	<!-- 底部 -->
  </body>
  <script type="text/javascript">
	
  </script>
</html>
