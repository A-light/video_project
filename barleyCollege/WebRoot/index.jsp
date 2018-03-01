<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大麦学院</title>
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
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
 	<body>
 		<% 
 			//session.setAttribute("username", null);
			//session.setAttribute("shenfen", null);
 		%>
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


		<!-- 轮换 -->
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="images/1.jpg" alt="..." style="width:100%;height:100%">
					<div class="carousel-caption">
						这里是介绍图片的文字
					</div>
				</div>
				<div class="item">
					<img src="images/2.jpg" alt="...">
					<div class="carousel-caption">
						这里是介绍图片的文字
					</div>
				</div>
				<div class="item">
					<img src="images/3.jpg" alt="...">
					<div class="carousel-caption">
						这里是介绍图片的文字
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="fa fa-arrow-left white top-50" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="fa fa-arrow-right white top-50" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!-- 轮换 -->
		<!-- 中间 -->
		<div class="container">
			<!-- item1 -->
			<div class="title">计算机</div>
			<div class="row" id="computer">
				<div style="display:none;">
					 <a style="color: #333;" id="course_href" href="#">
						<div class="col-md-3">
						<div class="panel panel-danger">
							<div class="panel-heading" id="course_title"></div>
							<div class="panel-body" id="course_content">
							</div>
						</div>
						</div>
					</a>
				</div>
				
			</div>
			<!-- item1 -->
			<!-- item2 -->
			<div class="title">外语</div>
			<div class="row" id="language">
				<div style="display:none;">
					 <a style="color: #333;"id="course_href" href="#">
						<div class="col-md-3">
						<div class="panel panel-danger">
							<div class="panel-heading" id="course_title"></div>
							<div class="panel-body" id="course_content">
							</div>
						</div>
						</div>
					</a>
				</div>
			</div>
			<!-- item2 -->
			<!-- item3 -->
			<div class="title">法学</div>
			<div class="row"  id="law">
				<div style="display:none;">
					 <a style="color: #333;" href="#">
						<div class="col-md-3">
						<div class="panel panel-danger">
							<div class="panel-heading" id="course_title"></div>
							<div class="panel-body" id="course_content">
							</div>
						</div>
						</div>
					</a>
				</div>
			</div>
			<!-- item3 -->
			<div class="title">名师简介</div>
			<div class="row">
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="images/head1.jpg" alt="..." class="circle">
					<div class="caption">
						<h4 class="index-username">Thumbnail label</h4>
						<p class="index-userinfo">计算机专业</p>
						<p class="index-userinfo">这里是介绍老师的的滴滴滴弟弟多多多多多多多多多多多多滴滴滴弟弟多多多多多多多多多多多多多</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="images/head2.jpg" alt="..." class="circle">
					<div class="caption">
						<h4 class="index-username">Thumbnail label</h4>
						<p class="index-userinfo">计算机专业</p>
						<p class="index-userinfo">这里是介绍老师的的滴滴滴弟弟多多多多多多多多多多多多滴滴滴弟弟多多多多多多多多多多多多多</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="images/head3.jpg" alt="..." class="circle">
					<div class="caption">
						<h4 class="index-username">Thumbnail label</h4>
						<p class="index-userinfo">计算机专业</p>
						<p class="index-userinfo">这里是介绍老师的的滴滴滴弟弟多多多多多多多多多多多多滴滴滴弟弟多多多多多多多多多多多多多</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="images/head4.jpg" alt="..." class="circle">
					<div class="caption">
						<h4 class="index-username">Thumbnail label</h4>
						<p class="index-userinfo">计算机专业</p>
						<p class="index-userinfo">这里是介绍老师的的滴滴滴弟弟多多多多多多多多多多多多滴滴滴弟弟多多多多多多多多多多多多多</p>
					</div>
				</div>
			</div>
			</div>
		</div>
		<script type="text/javascript">
		var type=["计算机","外语","法学"];
		$(document).ready(
			function(){
				for(var i=0;i<type.length;i++){
					$.ajax({
						cahe:true,
						type:"POST",
						url:"<c:url value='/CourseServlet?method=loadCourseByType'/>",
						async:false,
						data:{type_of:type[i]},
						error: function(request) {
						     alert("Connection error");
						   },
					   success: function(data) {
						  //  alert(data);
							var courses=jQuery.parseJSON(data);
							var root;
							console.warn(courses);
							for(var j=0;j<courses.length;j++){
							    console.log(courses[j]);
								var type=courses[j].type_of;
								if(type=="计算机"){
									root=$("#computer");
								}else if(type=="法学"){
									root=$("#law");
								}else if(type=="外语"){
									root=$("#language");
								}
								//course.addClass();
								var course_content=root.children(":first").html();
								root.append(course_content);
								var course=root.children(":last");
								course.find("#course_title").html(courses[j].course_name);
								course.attr("href","video.jsp?"+"&course_name="+courses[j].course_name
										+"&video_url="+courses[j].video_url+"&course_id="+courses[j].id+"&course_content="+courses[j].course_content)
								console.log("课程名"+courses[j].course_name);
								course.find("#course_content").html(courses[j].course_content);
								//root.children(":last").css('display','block'); 
								
							}
						}
					});
				}
			});
		</script>
		<!-- 中间 -->
		<div class="footer">
		</div>
		<script >
			$(document).ready(function() {
			    bodyContent = $.ajax({
			        url:" <%=path%>/footer.jsp",
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
