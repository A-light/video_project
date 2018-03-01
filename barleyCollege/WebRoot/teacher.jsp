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

<title>教师主页</title>
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
<script src="js/bootstrap-datetimepicker.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/personalPage.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datetimepicker.css" />

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
	<%
		session.setAttribute("isActive", "active");
		//session.setAttribute("username", "gw");
		//session.setAttribute("courses","null");
	%>
	<!-- 中间 -->
	<div class="personal-header">
		<div class="user-personnal-pic">
			<img src="images/head1.jpg" alt="..."
				class="img-responsive img-circle"> <br />
			<p class="text-center user-personnal-pic-username">
					${username} <i class="fa fa-mars" aria-hidden="true"></i> 
					<i class="fa fa-venus" aria-hidden="true"></i> <span
					class="label label-info"> ${shenfen} </span>
			</p>
			<p class="text-center user-personnal-pic-profile">个人简介：我是一个好老师</p>
		</div>
	</div>
	<div class="personal-body container" style="background-color: #fff">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation">
				<a href="#home" aria-controls="home" role="tab" data-toggle="tab"  class="openIndex">
					主页
				</a>
			</li>
		
			<li role="presentation" id="create" >
				<a  href="#courseContent" aria-controls="course" role="tab" data-toggle="tab" onclick="">
					我所上传的课程
				</a>
			</li>
			<script>
			function createXMLHttpRequest() {
				try {
					return new XMLHttpRequest();
				} catch (e) {
					try {
						return new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e) {
						return new ActiveXObject("Micorsoft.XMLHTTP");
					}
				}	   
			}
			$(document).ready(
				window.onload=function(){
					var xmlHttp = createXMLHttpRequest();
					// 打开连接
					xmlHttp.open("post", "<c:url value='/LoadCourseServlet'/>", true);
					xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					// 发送
					xmlHttp.send("username="+"${username}");
					xmlHttp.onreadystatechange = function(){ 
						if(xmlHttp.readyState == 4&&xmlHttp.status == 200) {
							var proArray=eval("("+xmlHttp.responseText+")");
							for(var i = 0; i < proArray.length; i++) {;
								var pro=proArray[i];
								var course_name=pro.course_name;
							}
						}	
					};
				}
			)
			</script>
			
			<li role="presentation" id="supervise">
				<a href="#courseListener" aria-controls="profile" role="tab" data-toggle="tab">
				我的课程进度监督
				</a>
			</li>
			
		</ul>

		<!--内容-->
	
		<div class="tab-content">
			<div style="min-height:500px; " role="tabpanel" class="tab-pane active" id="home" >
				<h3  >
					欢迎<%=session.getAttribute("username") %>
				</h3>
				<h3>
					课程创建情况<%=session.getAttribute("msg") %>
				</h3>
			</div>
		
			<div style="min-height:500px;" role="tabpanel" class="tab-pane " id="courseContent">
				<% if(session.getAttribute("username")==null){ %>
				<h3>你还没登录！请在右上角登录！</h3>
				<%}else{ %>
				<div class="row">
				       <c:forEach items="${sessionScope.courses}" var="course" >
				       <a href="<c:url value="video.jsp?video_url=${course.video_url}
				       	&course_name=${course.course_name}&course_id=${course.id}"/>">
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
					 <div class="col-md-3">
						<a href="#" data-toggle="modal" data-target=".create-course"
							class="btn btn-danger">创建</a>
						<!--<button >创建</button>-->
					</div>
				</div>
				<%} %>
			</div>
			<div style="min-height:500px;" role="tabpanel" class="tab-pane"  id="courseListener">
				<div id="supervise_title" stlye="display:none;">
					<h4 id="supervise_name"></h4>
				</div>
				<div id="my_students" stlye="display:none;">
						<div class="panel-group" id="accordion" role="tablist"
							aria-multiselectable="true">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="headingOne">
									<h4 class="panel-title">
										<a role="button" id="supervise_student" data-toggle="collapse" 
										data-parent="#accordion" href="#supervise_playtime" aria-expanded="true"
										aria-controls="collapseOne" > 
										学生 #1 
										</a>
									</h4>
								</div>
								<div  id="supervise_playtime" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="headingOne">
									<div class="panel-body" >
									学到了XX时XX分XX秒
									</div>
								</div>
							</div>
						</div>
				</div>
			<script type="text/javascript">
				$(document).ready(function(){
					var username="<%=session.getAttribute("username") %>";
					$("#supervise").click(
						function(){
							$.ajax({
								cahe:true,
								type:"POST",
								url:"<c:url value='/TeacherServlet?method=supervise'/>",
								async:false,
								data:{username:username},
								error: function(request) {
								     alert("Connection error");
								   },
							   success: function(data) {
								  //  alert(data);
									var supervise=jQuery.parseJSON(data);
								//	console.log(data);
									console.warn(supervise);
									var root=$("#courseListener");
									alert(supervise.length);
									for(var j=0;j<supervise.length;j++){
									  	var student=supervise[j];
									  	var supervise_title=root.children("#supervise_title:first").html();
										root.append(supervise_title);
										supervise_name=root.children("#supervise_name:last");
										supervise_name.html(student.course_name);
									    for(var i=0;i<student.progress.length;i++){
									    	var progress=student.progress[i];
									    	var my_student=root.children("#my_students:first");
									    	var student_content=my_student.html();
									    	root.append(student_content);
									    	//alert(student_content);
									    	root.find(".panel-title:last>#supervise_student").html("学生"+i+":"+progress.user_id);
									    	root.find(".panel-title:last>#supervise_student").attr('href','#student_play'+progress.course_id+i); 
									    	root.find(".panel-heading:last").next().attr('id','#student_play'+progress.course_id+i);
									    	root.find(".panel-body:last").html("学习进度："+progress.playtime)
									    	//alert(progress.user_id+" "+progress.playtime);
									    	;
									    }
									}
								}
							});
						}		
					);
					});
				</script>
				<h4>课程名称</h4>
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">

						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne"> 学生 #1 </a>
							</h4>
						</div>

						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">学到了XX时XX分XX秒</div>
						</div>
					</div>
				</div>
			</div>
		</div>
				<!-- 创建课程 -->
				
				
				<div class="modal fade create-course" tabindex="-1" role="dialog"
					aria-labelledby="mySmallModalLabel">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title text-center">课程创建</h4>
							</div>
							<div class="modal-body">
								<form action="<%=request.getContextPath()%>/UploadServlet?username=${username}"
									method="post" enctype="multipart/form-data">
									<div class="form-group">
										<label for="input_coursename">课程名：</label>
										 <input
											name="courseName" id="input_coursename" placeholder="请输入课程名"
											class="form-control" required="required">
									</div>
									<div class="form-group">
										<label for="input_courseType">课程类型：</label> 
										<select
											name="courseType">
											<option value="计算机">计算机</option>
											<option value="土木">土木</option>
											<option value="法学">法学</option>
											<option value="外语">外语</option>
											<option value="化工">化工</option>
											<option value="物理">物理</option>
										</select>
									</div>
									<div class="form-group">
										<label>课程简介：</label>
										<textarea class="form-control" id="course_content"
											name="courseContent"></textarea>
									</div>
									<div class="form-group">
										<label for="input_create_time">开课时间：</label>
										 <input
											type="datetime" name="beginTime" id="input_begin_time"
											placeholder="请输入开课时间" required="required" class="form-control">
									</div>
									<div class="form-group">
										<label for="input_ending_time">截课时间：</label>
										 <input
											type="datetime" name="endingTime" id="input_ending_time"
											placeholder="请输入截课时间" required="required" class="form-control">
									</div>
									<div class="form-group">
										<label for="upload_course">点击上传课程</label>
										 <input type="file"
											name="video" id="upload_course" required="required">
										<p class="help-block">请在此处上传课程文件</p>
									</div>
									<div class="form-group">
										<label for="upload_homework">点击上传作业</label>
										 <input type="file"
											name="homework" id="upload_homework" required="required">
										<p class="help-block">请在此处上传课程作业</p>
									</div>
									<button type="submit" class="btn btn-primary btn-block">确定</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			<!-- 登录 -->
			
		<!--内容-->

				<!--分页-->
				<div class="text-center fenye">
					<span class="fa fa-arrow-left white" aria-hidden="true">上一页</span>
					[1][2] 下一页<span class="fa fa-arrow-right white" aria-hidden="true"></span>
				</div>
				<!--分页-->
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
			$(document).ready(function(){
				$('#input_begin_time').datetimepicker({
					language:  'zh-CN',
					format: 'yyyy-mm-dd',
			        showMeridian: true,
			        autoclose: true,
			        todayBtn: true
				});
				$('#input_ending_time').datetimepicker({
					language:  'zh-CN',
					format: 'yyyy-mm-dd',
			        showMeridian: true,
			        autoclose: true,
			        todayBtn: true			
				});
			});
		</script>

</body>
</html>
