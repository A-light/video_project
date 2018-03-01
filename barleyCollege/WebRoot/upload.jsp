<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form action="<%=request.getContextPath()%>/UploadServlet" method="post" enctype="multipart/form-data">
  		 用户名：<input type="text" name="username"/><br/>
  		 用户名：<input type="text" name="usernamedfsf"/><br/>
  		 用户名：<input type="text" name="usernaddffdme"/><br/>
  		 <label>啦啦</label>
  		 用户名：<input type="text" name="usernamdfdsf"/><br/>
    	照片：<input type="file"  name="pic"/><br/>
    	<input type="submit" value="上传"/>
  	</form>
  	<c:url value=""></c:url>
  </body>
</html>
