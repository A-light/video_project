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

<title>课程</title>
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
<link rel="stylesheet" href="css/course.css">
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
	
	<!--主模块-->
	<div class="container course-select-container" style="min-height: 600px;">
		<!-- 分类 -->
		<div class="course-select">
			<div class="course-type">
				<ul class="course-url" role="tablist" style="list-style:none; ">
					<li class="course-li course-li-title">方向：</li>
					<li class="course-li active" role="presentation" id="allCourse"  >
						<a style="color: #1a1b1d;" href="#home" aria-controls="home" role="tab" data-toggle="tab" >
							全部
						</a>
					</li>
					<li class="course-li" role="presentation" >
						<a  style="color: #1a1b1d;" href="#computer" aria-controls="computer" role="tab" data-toggle="tab">
							计算机
						</a>
					</li>
					<li class="course-li" role="presentation">
					<a  style="color: #1a1b1d;" href="#literature" aria-controls="literature" role="tab" data-toggle="tab">
							文学
						</a>
					</li>
					<li class="course-li">
					<a  style="color: #1a1b1d;" href="#law" aria-controls="law" role="tab" data-toggle="tab">
							法学
						</a>
					</li>
					<li class="course-li">
						<a  style="color: #1a1b1d;" href="#language" aria-controls="language" role="tab" data-toggle="tab">
							外语
						</a>
					</li>
					<li class="course-li">
						<a  style="color: #1a1b1d;" href="#philosophy" aria-controls="philosophy" role="tab" data-toggle="tab">
							哲学
						</a>
					</li>
					<li class="course-li">
					<a   style="color: #1a1b1d;" href="#art " aria-controls="art " role="tab" data-toggle="tab">
							艺术
						</a>
					</li>
				</ul>
			</div>
			<hr>
		</div>
		<!-- 分类 -->

		<!-- 内容 -->
		
		<div class="sort">
			<button class="sort-b sort-b-active">热度</button>
			<button class="sort-b">新度</button>
		</div>
		<script>
			
		</script>
		<div class="tab-content">
		   <div role="tabpanel" class="tab-pane active" id="home" style="min-height: 500px;">
			<div class="row"  id="home_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane" id="computer" style="min-height: 500px;">
				<div class="row"  id="computer_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane " id="literature" style="min-height: 500px;">
			<div class="row"  id="literature_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane " id="law" style="min-height: 500px;">
			<div class="row"  id="law_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane " id="language" style="min-height: 500px;">
			<div class="row"  id="language_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane " id="philosophy" style="min-height: 500px;">
			<div class="row"  id="philosophy_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
		  <div role="tabpanel" class="tab-pane" id="art" style="min-height: 500px;">
				<div class="row"  id="art_content">
				<div style="display:none;">
					 <a href="#" style="color: #333;">
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
		 </div>
	 </div>
		<!-- 内容 -->
	</div>
	<!--主模块-->
		<script type="text/javascript">
		var type=["全部","计算机","外语","法学","文学","哲学","艺术"];
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
							var len=courses.length;
							if(len>16) len=16;
							if(type[i]=="全部"){
								for(var j=0;j<len;j++){
								root=$("#home_content");
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
							}else{
								for(var j=0;j<len;j++){
								    //console.log(courses[j]);
									var type_of=courses[j].type_of;
									if(type_of=="计算机"){
										root=$("#computer_content");
									}else if(type_of=="法学"){
										root=$("#law_content");
									}else if(type_of=="外语"){
										root=$("#language_content");
									}else if(type_of=="文学"){
										root=$("#literature_content");
									}else if(type_of=="艺术"){
										root=$("#art_content");
									}else if(type_of=="哲学"){
										root=$("#philosophy_content");
									}else{
										alert("出错！");
									}
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
								//course.addClass();
							}
						}
					});
				}
			});
		</script>
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
</body>
</html>
