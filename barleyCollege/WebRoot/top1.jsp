<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!-- 导航条 -->
<html>
<body> 
<nav
	class="navbar navbar-inverse no-border-r no-margin-b navbar-fixed-top">
<div class="container-fluid">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="index.jsp"/>">首页</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="<c:url value="course.jsp"/>">课程 <span class="sr-only">(current)</span></a>
			</li>
			<li><a href="<c:url value="blog.jsp"/>">社区</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<% if(session.getAttribute("user")==null){ %>
				<li id="top_login"><a href="#" data-toggle="modal"
				data-target=".bs-example-modal-sm">登录</a></li>
			<% }else {%>
			<li></li>
			<% }%>
			<li><a href='<c:url value="signIn.jsp"/>'>注册</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">欢迎${username} <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><%if(session.getAttribute("shenfen")=="学生") {%>
					<a href="<%=request.getContextPath()%>\student.jsp">个人主页</a>
					<%}else if(session.getAttribute("shenfen")=="老师"){ %><a href="<%=request.getContextPath()%>\teacher.jsp">个人主页</a><%} %>
					</li>
					
					<li><a href="">修改密码</a></li>
					<!--<li role="separator" class="divider"></li>-->
					
					<li><a href="<%session.setAttribute("user", null);	
							session.setAttribute("username", null);	
							session.setAttribute("shenfen", null);	
					%><%=request.getContextPath()%>\index.jsp">退出登录</a></li>
				</ul></li>
		</ul>
	</div>
</div>
</nav>
<!-- 导航条 -->
<!-- 登录 -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-center">用户登录</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value='/UserServlet?method=login'/>"
						method="post">
						<div class="form-group">
							<label for="input_username">姓名：</label> 
							<input type="text"  style="width:80%;"
								name="username" id="input_username" required="required" placeholder="请输入用户名">
						</div>
						<div class="form-group">
							<label for="input_password">密码：</label> 
							<input type="password"  style="width:80%;"
								name="password" id="input_password" required="required" placeholder="请输入密码">
						</div>
						<div class="checkbox">
							<label> <input type="checkbox"> 记住我</label> 
							<a href="#" class="float-right">忘记密码？</a>
						</div>
						<button type="submit" class="btn btn-default btn-block">
							登录</button>
					</form>
				</div>
			</div>
		</div>
</div>
</body>
<!-- 登录 -->
</html>