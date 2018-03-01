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

<title>test</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	
	<%
		session.setAttribute("isActive", "active");
		session.setAttribute("username", "gw");
	%>
	
	<div class="personal-body container" style="background-color: #fff">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation">
				<a href="#home" aria-controls="home" role="tab" data-toggle="tab"  class="openIndex">
					主页
				</a>
			</li>
		
			<li role="presentation" >
				<a id="create" href="#courseContent" aria-controls="course" role="tab" data-toggle="tab">
					我所上传的课程
				</a>
			</li>
				
			<li role="presentation">
				<a href="#courseListener" aria-controls="profile" role="tab" data-toggle="tab">
				我的课程进度监督
				</a>
			</li>
			
		</ul>

		<!--内容-->
	
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home" >
				<h3>
					欢迎<%=session.getAttribute("username") %>
				</h3>
			</div>

			<div role="tabpanel" class="tab-pane " id="courseContent">
				<div class="row">
				       <c:forEach items="${sessionScope.courses}" var="course" >
				       <a href="<c:url value="video.jsp"/>">
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
			<div role="tabpanel" class="tab-pane" id="courseListener">
				<h4>课程名称</h4>

			</div>
	</div>
	
	</div>
	<script type="text/javascript">

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
			$("#create").onclick = function() {
			var xmlHttp = createXMLHttpRequest();
			// 打开连接
			xmlHttp.open("post", "<c:url value='/LoadCourseServlet'/>", true);
			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			// 发送
			xmlHttp.send("username="+"gw");
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4&&xmlHttp.status == 200) {
					var proArray=eval("("+xmlHttp.responseText+")");
					for(var i = 0; i < proArray.length; i++) {;
						var pro=proArray[i];
						var course_name=pro.course_name;
					
					}
				}	
			};
		})
		
</script>
						
</body>

</html>
