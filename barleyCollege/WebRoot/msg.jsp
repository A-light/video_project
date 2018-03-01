<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'msg.jsp' starting page</title>

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

</head>
<body>
	<h1>${msg }</h1>
	<c:forEach items="${courses }" var="course">
		<ul>
			<li>${course }</li>
		</ul>
	</c:forEach>
	
	<%=request.getContextPath() %><br/>
	<%=session.getAttribute("msg")%><br/>
	<%=session.getAttribute("courseName") %><br/>
	<%=session.getAttribute("userId") %><br/>
	<%=session.getAttribute("courseContent")%><br/>
	<%=session.getAttribute("beginTime")%><br/>
	<%=session.getAttribute("endingTime")%><br/>
	<%=session.getAttribute("homoworkUrl") %><br/>
	<%=session.getAttribute("videoUrl") %><br/>
	<%=session.getAttribute("courseType") %><br>
</body>
</html>
