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
	<title>博客内容</title>
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
		

		<div style="min-height:900px;">
			  <div id="blog">
					<div style="display:none;" id="blog_content">
						<div class="media" style="width:100%;background-color: #fff;padding:20px;margin-top: 51px;">
							  <div class="media-left media-middle">
							    <a href="#">
							      <img class="media-object" src="images/index.svg" alt="...">
							    </a>
							  </div>
							  <div class="media-body">
							    <h4 class="media-heading" > 
							    <span id="blog_title">标题</span>
							    <a href="#" class="pull-right" >
							     <span id="blog_username"></span>
							    <i class="fa fa-star-o"></i> 关注Ta
							    </a>
							    <a href="#" class="btn btn-link">修改</a><a href="#" class="btn btn-link" >删除</a>
							    </h4>
							    <p id="blog_content">
							   		
							    </p>
							    <span id="blog_uppdated_time"></span>
							  </div>
						</div>
					</div>
				</div>
					<script>
					$(document).ready(
						function(){
							$.ajax({
								cahe:true,
								type:"POST",
								url:"<c:url value='/BlogServlet?method=doBlogContent'/>",
								async:false,
								data:{user_id:"<%=request.getParameter("user_id")%>",
									  blog_id:<%=request.getParameter("blog_id")%>},
								error: function(request) {
								     alert("Connection error");
								   },
							   success: function(data) {
									var blog=jQuery.parseJSON(data);
									console.warn(blog);
									var root=$("#blog");
									var blog_content=$("#blog_content").html();
										root.append(blog_content);
										var child=root.children(":last");
										//alert(blog.user_id+blog.blog_username+blog.blog_title);
										child.find("#blog_title").html(blog.blog_title);
										child.find("#blog_content").html(blog.blog_textcontent);
										child.find("#blog_username").html(blog.blog_username);
										child.find("#blog_uppdated_time").html(blog.blog_updated_time);
								}
							})
						}
					)
				</script>
			<div class="container" id="blog_comments">
				<div style="display:none;" id="blog_comment">
					<div style="width:100%;background-color: #fff;box-shadow: 0 4px 8px 0 rgba(7,17,27,.1);margin:30px 0px 0px 0px">
						<div style="padding: 20px 20px 10px">
							<img src="images/index.svg" alt="">
							<strong style="font-size:16px;margin-left:20px;">
								<span id="comment_username"></span>
							 <i class="fa fa-trophy" style="color:red">
							 100000</i>
							 </strong> 
							 <a href="#" class="pull-right"><i class="fa fa-star-o">
							 </i> 关注Ta</a>
							<br>
							<p id="comment_content">这里是评论内容</p>
							<!-- 
							<p style="margin-top:10px;"><small>由<a href="#">XXX</a
							>于2107年4月21号评论·最后由<a href="#">XXX</a>			</p>
							-->	
							<p style="margin-top:10px;">
							 <span id="comment_time">2017</span>
							<a href="#" class="pull-right"><i class="icon-thumbs-up	-alt"></i>1</a>
							</small> 	</p>	
												
						</div>
					</div>
				</div>
			</div>
			<script>
				function loadComment(){
					$.ajax({
						cahe:true,
						type:"POST",
						url:"<c:url value='/BlogServlet?method=loadBlogComment'/>",
						async:false,
						data:{user_id:"<%=request.getParameter("user_id")%>",
							  blog_id:<%=request.getParameter("blog_id")%>},
						error: function(request) {
						     alert("Connection error");
						   },
					   success: function(data) {
							var blogcoments=jQuery.parseJSON(data);
							console.warn(blogcoments);
							var root=$("#blog_comments");
							var blog_content=$("#blog_comment").html();
							for(var i=0;i<blogcoments.length;i++){
								blogcoment=blogcoments[i];
								root.append(blog_content);
								var child=root.children(":last");
								child.find("#comment_username").html(blogcoment.comment_username);
								child.find("#comment_content").html(blogcoment.comment_content);
								child.find("#comment_time").html(blogcoment.comment_time);
							}
						}
					})
				}
				$(document).ready(
						function(){
							loadComment();
					})
			</script>
		<div class="container">
				<%String username=(String)session.getAttribute("username");%>
				<%if(username==null){ %>
				<div class="alert alert-danger" style="border-radius: 0px;margin-top:20px;">
					<strong>温馨提示 <i class="fa fa-flag"></i></strong>
					<p>需要在右上角登录后才能进行评论</p>
				</div>
				<% }else{%>
				<form style="padding:40px 0px 20px 0px" id="sub_commentFrom" method="post">
					<textarea class="form-control" placeholder="我也来说几句" name="input_comment"></textarea>
					<div style="margin-top: 20px;">
						<input type="hidden" name="username" value="<%=username %>"/>
						<input type="hidden" name="blog_id" value="<%=request.getParameter("blog_id")%>"/>
						<button class="btn btn-primary" id="sub_comment">评论</button>
						<span style="font-size:16px;vertical-align: middle;">
						<!-- 或者使用 <kbd><kbd>ctrl</kbd> + <kbd>Enter</kbd></kbd></span>--> 
					</div>
				</form>
				<script>
				$("#sub_comment").click(function(){$.ajax({
					  cache: true,
					  type: "POST",
					  url:"<c:url value='/BlogServlet?method=doBlogComment'/>",
					  data:$('#sub_commentFrom').serialize(),// 你的formid
					  async: false,
					  error: function(request) {
					     alert("Connection error");
					   },
					   success: function(data) {
						   alert("评论成功！")
						  // loadComment();
					   }
					});
				});
				</script>
				<%}%>
		 </div>
	</div>
	<div class="footer">
	</div>


	<script >
		$(document).ready(function() {
		    bodyContent = $.ajax({
		        url: " <%=path%>/footer.jsp",
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
