<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

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
	<div class="personal-body container" style="background-color: #fff">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation">
				<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
					主页
				</a>
			</li>
		
			<li role="presentation" class="active">
				<a href="#course" aria-controls="course" role="tab" data-toggle="tab">
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
		<%
			session.getAttributeNames();
		%>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane" id="course">
				<h3>
					欢迎<%=session.getAttribute("username") %>
				</h3>
			</div>
			<% %>
			<div role="tabpanel" class="tab-pane" id="course">
				<div class="row">
				
					<c:forEach items="${array}" var="arr">
						<div class="col-md-3">
							<div class="panel panel-primary">
								<div class="panel-heading">Panel heading without title</div>
								<div class="panel-body">Panel content Panel content Panel
								content Panel content Panel content Panel content Panel content
								Panel content</div>
							</div>
						</div>
					</c:forEach>
					
				</div>
			</div>
			
			<div role="tabpanel" class="tab-pane" id="courseListener">
				<h4>课程名称</h4>


			</div>
	</div>
	
	</div>

	
	
	
	
	
	
	
	
	
	
	
	<script>
	$(document).ready(function() {
	    bodyContent = $.ajax({
	    	url: "<%=path%>/footer.jsp",
	        global: false,
	        type: "POST",
	        dataType: "html",
	        async: false,
	        success: function(msg) {
	            $(".course").append(msg);
	        }
	    })
	</script>
	<script type="text/javascript">
	// 文档加载完成后
	// 加载所有省份名称
	$(document).ready( 
		function() {
		/*
		请求服务器，加载所有省名称到<select>中
		*/
		/*
		1. ajax四步
		*/
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.onreadystatechange = callback;//服务器响应完成后执行callback函数
		xmlHttp.open("POST", "/LoadCourseServlet", true);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send("course"=<%=session.getAttribute("username")%>);

	 })
	// 本方法获取服务器响应的所有省份的名称
	function callback() {
		if(this.readyState == 4 && this.status == 200) {
			var person = eval("(" + json + ")");
		
			document.getElementById("province").onchange = loadCities;
		}
	}
	// 本函数在province的<select>元素发送变化时执行！
	// 本函数会使用当前选中的省份名称为参数，向服务器发送请求，获取当前省份下的所有城市！
	function loadCities() {
		var proName = this.value;//获取<select>选择的省份名称
		/*
		AJAX4步
		*/
		var xmlHttp = createXMLHttpRequest();//创建异常对象
		// 指定回调函数
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 得到服务器响应的xml文档对象
				var doc = xmlHttp.responseXML;//注意，这里使用的是resopnseXML属性，不是resopnseText
				// 获取文档中所有city元素
				var cityElementList = doc.getElementsByTagName("city");
				// 获取html元素：city的<select>
				var citySelect = document.getElementById("city");
				// 删除city的<select>元素的所有子元素
				removeChildNodes(citySelect);
				
				// 创建<option>元素，指定文本内容为“请选择”
				var qxzOption = document.createElement("option");
				var textNode = document.createTextNode("===请选择===");
				qxzOption.appendChild(textNode);
				// 把"请选择"这个<option>添加到<select>元素中
				citySelect.appendChild(qxzOption);
				
				// 循环遍历每个服务器端响应的每个<city>元素
				for(var i = 0; i < cityElementList.length; i++) {
					var cityEle = cityElementList[i];
					var cityName = null;
					// 获取<city>元素的文本内容！处理浏览器差异！
					if(window.addEventListener) {
						cityName = cityEle.textContent;
					} else {
						cityName = cityEle.text;
					}
					// 使用城市名称创建<option>，并添加到<select>元素中
					addCityOption(cityName);
				}
			}
		};
		xmlHttp.open("POST", "/ajaxdemo1/CityServlet", true);//打开与服务器的连接
		// 因为是POST请求，所以要设置Content-Type请求头
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		// 参数为当前选中的省份名称
		xmlHttp.send("provinceName=" + proName);	
	}
	// 使用proName创建<option>元素添加到<select>元素中
	function addProvinceOption(proName) {
		var option = document.createElement("option");//创建<option>元素
		var textNode = document.createTextNode(proName);//使用省份名称创建文本节点
		option.appendChild(textNode);//把省份名称的文本节点添加到<option>元素中
		option.setAttribute("value", proName);//使用省份名称来设置<option>元素的value属性
		document.getElementById("province").appendChild(option);//把<option>元素添加到<select>元素中　
	}
	// 本函数用来创建城市的<option>，并添加到<select>元素中
	function addCityOption(cityName) {
		var citySelect = document.getElementById("city");//获取id为city的<select>
		var cityOption = document.createElement("option");//创建<option>元素
		var textNode = document.createTextNode(cityName);//使用城市名称创建文本节点
		cityOption.appendChild(textNode);//把文本节点添加到<option>元素中
		cityOption.setAttribute("value", cityName);//设置<option>元素的value属性为城市名称
		citySelect.appendChild(cityOption);//把<option>元素添加到<select>元素中
	}
	//删除指定元素的所有子元素
	function removeChildNodes(ele) {
		var nodes = ele.childNodes;//获取当前元素的所有子元素集合
		while(nodes.length > 0) {//遍历所有子元素
			ele.removeChild(nodes[0]);//删除子元素
		}
	}

	</script>
</body>
</html>
