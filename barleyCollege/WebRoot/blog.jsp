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

<title>社区</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
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
	<!-- 中间 -->
	<div class="container" style="min-height:900px; margin-top: 51px;" id="blog">
		<div style="display:none" id="blog_content">
			<div 
			style="width:100%;background-color: #fff;box-shadow: 0 4px 8px 0 rgba(7,17,27,.1);
			margin:30px 0px 0px 0px;">
				<div style="padding: 20px 20px 10px">
					<strong style="font-size:16px;">
					<a href="content.jsp" id="blog_title"></a>
						<i class="fa fa-fire" style="color:red"></i>
					</strong>
					<p style="margin-top:10px;">
						<small>
						由<a href="" id="blog_username">XXX</a>于<span id="blog_created_time"></span>
						<!--最后由<a href="#">XXX</a>于XXXX年XX月XX号回复  -->
						</small> <i class="fa fa-comments-o pull-right" style="font-size:40px;">
							3</i>
					</p>
				</div>
			</div>
		</div>
		<!-- <div 
			style="width:100%;background-color: #fff;box-shadow: 0 4px 8px 0 rgba(7,17,27,.1);
			margin:30px 0px 0px 0px;">
				<div style="padding: 20px 20px 10px">
					<strong style="font-size:16px;">
					<a id="blog_title" href="content.jsp" >这里是标题,可以长一点没关系</a>
						<i class="fa fa-fire" style="color:red"></i>
					</strong>
					<p style="margin-top:10px;">
						<small>
						由<a href="" id="blog_username"></a>用户于<span id="blog_created_time">时间发布</span>
						<!--最后由<a href="#">XXX</a>于XXXX年XX月XX号回复  -->
						<!--  </small> <i class="fa fa-comments-o pull-right" style="font-size:40px;">
							3</i>
					</p>
				</div>
			</div>
		 -->
	</div>
	<script>
		$(document).ready(
			function(){
				$.ajax({
					cahe:true,
					type:"POST",
					url:"<c:url value='/BlogServlet?method=loadBlog'/>",
					async:false,
					data:null,
					error: function(request) {
					     alert("Connection error");
					   },
				   success: function(data) {
						var blogs=jQuery.parseJSON(data);
						console.warn(blogs);
						var root=$("#blog");
						var blog_content=$("#blog_content").html();
						for(var i=0;i<blogs.length;i++){
							var blog=blogs[i];
							root.append(blog_content);
							var child=root.children(":last");
							//alert(blog.user_id+blog.blog_username+blog.blog_title);
							child.find("#blog_title:last").attr('href','content.jsp?'
									+"user_id="+blog.user_id+"&blog_id="+blog.blog_id);
							child.find("#blog_title:last").html(blog.blog_title);
							child.find("#blog_username:last").html(blog.blog_username);
							child.find("#blog_created_time:last").html(blog.blog_created_time);
						}
					}
				})
			}
		)
	</script>
	<div class="footer"></div>
	<script>
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
</body>
</html>
