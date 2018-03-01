<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>博客发表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<body style="background-color: #f3f5f7">

	<div class="toper">
	</div>
		

	<script >
		$(document).ready(function() {
		    bodyContent = $.ajax({
		        url: "<%=path%>/top.jsp",
		        global: false,
		        type: "POST",
		        dataType: "html",
		        async: false,
		        success: function(msg) {
		            $(".toper").append(msg);
		        }
		    })
		});
		</script>
	<div class="container" style="margin-top: 51px;">
		<form action="BlogServlet?method=doBlog" method="post">
	  		<div class="form-group">
		   		<label for="blog_title">标题</label>
		    	<input type="text" class="form-control" id="blog_title" name="title" placeholder="请输入文章的标题">
	  		</div>
	  		<div class="form-group">
	    	<label for="blog_content">内容</label>
	    	<textarea rows="25" id="blog_content" class="form-control" name="text_content" placeholder="请输入文章的内容"></textarea>
	  		</div>
	  			<input type="hidden" name="username" value="<%=session.getAttribute("username") %>"/>
	  		<button type="submit" class="btn btn-primary btn-block">提交</button>
		</form>
	</div>


	
	<div class="footer">
	</div>


	<script >
		$(document).ready(function() {
		    bodyContent = $.ajax({
		        url: "<%=path%>/footer.jsp",
		        global: false,
		        type: "POST",
		        dataType: "html",
		        async: false,
		        success: function(msg) {
		            $(".footer").append(msg);
		        }
		    })
		});
	</script>
</body>
</html>
