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

<title>学生主页</title>
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
<link rel="stylesheet" href="css/personalPage.css">
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
			$.post("<c:url value='/StudentServlet?method=load'/>",function(result){
			  });
		});
	</script>
	<!-- 中间 -->
	<script>
		$(document).ready(
			function(){
			
			}
		)
		
	</script>
	<div class="personal-header">
		<div class="user-personnal-pic">
				<img src="images/head4.jpg" alt="..." class="img-responsive img-circle">
				<br/>
				<p class="text-center user-personnal-pic-username">
					${username} 
					<i class="fa fa-mars"  aria-hidden="true"></i> <i class="fa fa-venus" aria-hidden="true"></i>
					<span class="label label-warning">${sessionScope.shenfen}</span>
				</p>
				<p class="text-center user-personnal-pic-profile">个人简介：性别男爱好女</p>
		</div>
	</div>
	<div class="personal-body container">
		<ul class="nav nav-tabs" role="tablist">
    			<li role="presentation" id="myCollections" class="active">
    			<a href="#home" aria-controls="home" role="tab" data-toggle="tab">我的收藏</a>
    			</li>
    			<li role="presentation">
    			<a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">已完成</a>
    			</li>
    			<li class="pull-right">
    				<a href="">
    					<i class="fa fa-star-o" aria-hidden="true"></i>
    					（123）
    				</a>
    			</li>
    			<li class="pull-right">
    				<a href="">
    					<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
    					（123）
    				</a>
    			</li>
  		</ul>
  		<div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="home" style="min-height: 300px;">
		    	<div class="row">
		    		  <c:forEach items="${sessionScope.student_courses}" var="course" >
		    			 <a href="<c:url value="
		    			 video.jsp?video_url=${course.video_url}&course_name=${course.course_name}
		    			 			&course_content=${course.course_content}&course_id=${course.id} "/>">
							<div class="col-md-3">
								<div class="panel panel-primary">
									<div class="panel-heading">
										${course.course_name}
									</div>
									<div class="panel-body">
										${course.course_content }
									</div>
								</div>
							</div>
						</a>
					 </c:forEach>
		    	</div>
		    </div>
		    
		    
		    
		    
		    <div style="min-height: 300px;" role="tabpanel" class="tab-pane" id="messages">
		    	<div class="row">
		    		<div class="col-md-3">
			    		<div class="panel panel-danger">
			  				<div class="panel-heading">Panel heading without title</div>
			  				<div class="panel-body">
			    				Panel content
			    				Panel content
			    				Panel content
			    				Panel content
			    				Panel content
			    				Panel content
			    				Panel content
			    				Panel content
			  				</div>
						</div>
			    	</div>
		    	</div>
		    </div>
  		</div>
	  </div>
	</div>
	
	<!-- 中间 -->
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
</html>
