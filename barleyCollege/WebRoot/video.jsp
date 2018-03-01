<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.sightseeing.domain.*" %>
<%@ page language="java" import="javax.servlet.http.Cookie" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>视频</title>
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
<link rel="stylesheet" href="css/video.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="ckplayer/ckplayer.js" charset="utf-8"></script>
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
		<!--视频播放模块-->
		<div class="content video-player" id="video">
			<!--	<video src="video/animal.mp4" width="500px" height="400px" controls="controls">视频播放区域</video>				-->
			<div id="a1"></div>
			<div style="margin:0 auto;">
			 <input	type="button" name="false_button" value="切换到flash播放器" onClick="addflash();" />
	 		 <input type="button" name="html5_button" value="切换到html5播放器" onClick="addhtml5();" />
	 		</div>
		</div>	
		<!--视频播放模块-->
		<%
			Course course=new Course();
			String video_url=request.getParameter("video_url");
			String course_name=request.getParameter("course_name");
			String course_id=request.getParameter("course_id");
			String course_content=request.getParameter("course_content");
			//="/barleyCollege/files/videos/animal.mp4";
			String videoPath = "http://localhost:8080"+video_url;
			session.setAttribute("courseUrl", videoPath);
			session.setAttribute("course_name",course_name);
			String username=(String)session.getAttribute("username");
		%>
		
		<script type="text/javascript">
	     function timeHandler(time) {
	    	 var playtime="<%=username%>"+"_"+"<%=course_id%>";
	         if (time > -1) {
	              SetCookie(playtime, time);
	              console.log(time);
	          }
	      }
			var flashvars = {
				f: '<%=session.getAttribute("courseUrl")%>',
				a : '', //调用时的参数，只有当s>0的时候有效
				s : '0', //调用方式，0=普通方法（f=视频地址），1=网址形式,2=xml形式，3=swf形式(s>0时f=网址，配合a来完成对地址的组装)
				c : '0', //是否读取文本配置,0不是，1是
				x : '', //调用配置文件路径，只有在c=1时使用。默认为空调用的是ckplayer.xml
				e : '8', //视频结束后的动作，0是调用js函数，1是循环播放，2是暂停播放并且不调用广告，3是调用视频推荐列表的插件，4是清除视频流并调用js功能和1差不多，5是暂停播放并且调用暂停广告
				v : '30', //默认音量，0-100之间
				lv : '0', //是否是直播流，=1则锁定进度栏
				loaded : 'loadedHandler', //当播放器加载完成后发送该js函数loaded
				//调用播放器的所有参数列表结束
				my_url : encodeURIComponent(window.location.href) //本页面地址
			};
			var params = {
				bgcolor: '#FFF',
				allowFullScreen: true,
				allowScriptAccess: 'always',
				wmode: 'transparent'
			};
			var video = [ '<%=session.getAttribute("courseUrl")%>->video/mp4' ];
			CKobject.embed('ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '600', '400', false, flashvars, video, params);
			//如果你不需要某项设置，可以直接删除，注意var flashvars的最后一个值后面不能有逗号
			 function loadedHandler() {
				  addPauseListenner();
		          addPlayListener();
		          addEndedListenner();
		     }
		     function addTimeListener() {//增加时间监听
			    if (CKobject.getObjectById('ckplayer_a1').getType()) {//说明使用html5播放器
			           CKobject.getObjectById('ckplayer_a1').addListener('time', timeHandler);
			    }else {
			          CKobject.getObjectById('ckplayer_a1').addListener('time', 'timeHandler');
			       }
			 }
			 function addPlayListener() {//增加播放监听
			    if (CKobject.getObjectById('ckplayer_a1').getType()) {//说明使用html5播放器
			        CKobject.getObjectById('ckplayer_a1').addListener('play', playHandler);
			    }else {
			        CKobject.getObjectById('ckplayer_a1').addListener('play', 'playHandler');
		       }
		   }
			function playHandler() {
			    //alert('因为注册了监听播放，所以弹出此内容，删除监听将不再弹出');
			    //  removePlayListener();
			    $.ajax({
					cahe:true,
					type:"POST",
					url:"<c:url value='/VideoServlet?method=queryPlaytime'/>",
					async:false,
					data:{username:"<%=username%>",course_name:"<%=course_name%>",time:getCookie("<%=username%>"+"_"+"<%=course_id%>")},
					error: function(request) {
						   console.warn("查询出错了！");
					 },
					 success: function(data) {
						SetCookie("<%=username%>"+"_"+"<%=course_id%>", data);
					}
				});
	    		CKobject.getObjectById('ckplayer_a1').videoSeek(
				    	getCookie("<%=username%>"+"_"+"<%=course_id%>")
				  );
			     addTimeListener();
			}
			function addPauseListenner(){
			    if (CKobject.getObjectById('ckplayer_a1').getType()) {//说明使用html5播放器
			        CKobject.getObjectById('ckplayer_a1').addListener('pause', pauseHander);
			    }else {
			        CKobject.getObjectById('ckplayer_a1').addListener('pause', 'pauseHander');
			    }
			}
			function pauseHander(){
				var time=getCookie("<%=username%>"+"_"+"<%=course_id%>");
				//alert("时间:"+time);
			}
			function addEndedListenner(){
				 if (CKobject.getObjectById('ckplayer_a1').getType()) {//说明使用html5播放器
				     CKobject.getObjectById('ckplayer_a1').addListener('ended', VideoPlayEndedHandlerx);
				 }else {
				     CKobject.getObjectById('ckplayer_a1').addListener('ended', 'VideoPlayEndedHandlerx');
				 }
			}
			function VideoPlayEndedHandlerx(){
				alert("结束了");
				 $.ajax({
					cahe:true,
					type:"POST",
					url:"<c:url value='/VideoServlet?method=accomplish'/>",
					async:false,
					data:{username:"<%=username%>",course_name:"<%=course_name%>",time:getCookie("<%=username%>"+"_"+"<%=course_id%>")},
					error: function(request) {
						 console.warn("查询出错了！");
					 },
					success: function(data) {
						SetCookie("<%=username%>"+"_"+"<%=course_id%>", data);
					}
				});
			}
			function removeListener() { //删除监听事件
				if (CKobject.getObjectById('ckplayer_a1').getType()) { //说明使用html5播放器
					CKobject.getObjectById('ckplayer_a1').removeListener('play', playHandler);
				} else {
					CKobject.getObjectById('ckplayer_a1').removeListener('play', 'playHandler');
			    }
		   }
			CKobject.embed('ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '600', '400', false, flashvars, params);
		    //写cookies函数
	       function SetCookie(name, value)//两个参数，一个是cookie的名子，一个是值
	       {
		      var Days = 30; //此 cookie 将被保存 30 天
		      var exp = new Date(); //new Date("December 31, 9998");
		      exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		      document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
		   }
		    
		   function getCookie(name)//取cookies函数
		   {
		      var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
		      if (arr != null) return unescape(arr[2]);  return null;
		   }
		   function addflash() {
			if (CKobject.Flash()['f']) {
				CKobject._K_('a1').innerHTML = '';
				CKobject.embedSWF('ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '600', '400', flashvars, params);
			}else {
				alert('该环境中没有安装flash插件，无法切换');
			}
		  }
		  function addhtml5() {
			if (CKobject.isHTML5()) {
				support = [ 'all' ];
				CKobject._K_('a1').innerHTML = '';
				CKobject.embedHTML5('a1', 'ckplayer_a1', '100%', '100%', video, flashvars, support);
			} else {
				alert('该环境不支持html5，无法切换');
			}
		}
		 $(document).ready(function(){
			 setInterval(function(){console.warn("gw");},1000);
		 });
		</script>
		<script>
			
		</script>
		<!--评论区-->
		<div class="container" style="min-height:700px;padding-top:20px;background-color: #fff;margin-bottom: 20px;">
			<div class="col-md-9" style="border-right:1px solid #ccc">
				<h4>评论</h4><%=session.getAttribute("courseUrl")%>
				<hr>
				<form id="commentForm"  method="post">
					<div class="media">
					  <div class="media-left">
					    <a href="#">
					      <img class="media-object" src="images/index.svg" alt="...">
					    </a>
					  </div>
					  <div class="media-body">
					   	<textarea class="form-control" name="comment" required="required"></textarea>
					  </div>
					  <input name="username" type="hidden" value="${username}">
					  <input name="course_name" type="hidden" value="${course_name}">
					  <input id="sub" type="submit" class="btn btn-primary pull-right" style="margin-top:20px;" value="发表评论">
					</div>
				</form>
				<hr>
				<script>
					$("#sub").click(function(){$.ajax({
						  cache: true,
						  type: "POST",
						  url:"<c:url value='/VideoServlet?method=submitComment'/>",
						  data:$('#commentForm').serialize(),// 你的formid
						  async: false,
						  error: function(request) {
						     alert("Connection error");
						   },
						   success: function(data) {
							   loadComment();
						   }
						});
					});
					$(document).ready(function(){
						$.ajax({
							cahe:true,
							type:"POST",
							url:"<c:url value='/StudentServlet?method=queryStatus'/>",
							async:false
						});
						//loadComment();
						}
					);
				</script>
			<!-- <c:forEach items="${comments}" var="comment">
				<div class="media">
				  <div class="media-left">
				    <a href="#">
				      <img class="media-object" src="images/index.svg" alt="...">
				    </a>
				  </div>
				  <div class="media-body">
				    <h4 class="media-heading">${comment.author_name}</h4>
				   		${comment.comment_content}
					<p style="color:#93999f;margin-top: 10px;"><small>时间：${comment.comment_time}</small></p>
				  </div>
				</div>
				<hr>
			</c:forEach>
			 --> 
				
			<div id="course_comment">
				 <div id="course_comment_content" style="display:none;">
					<div class="media">
						  <div class="media-left">
						    <a href="<c:url value="student.jsp" />">
						      <img style="max-height:80px;" class="media-object" src="images/hx1.png" alt="这是头像">
						    </a>
						  </div>
						 <div class="media-body">
							 <h4 class="media-heading"></h4>
							 <p style="color:#93999f;margin-top: 10px;">
							<div id="commentMain"></div>
							<small id="commentTime"></small>
							<!--<small>时间：XXXX年XX月XX日XX时XX分XX</small>-->
							</p>
						 </div>
					</div>
				</div>
			</div>
			  <script type="text/javascript">
					var loadComment=function(){
						$.ajax({
							cahe:true,
							type:"POST",
							url:"<c:url value='/VideoServlet?method=loadComment'/>",
							async:false,
							data:{course_id:"<%=course_id%>"},
							error: function(request) {
							     alert("Connection error");
							   },
						   success: function(data) {
								var comments=eval("("+data+")");
								var root= $("#course_comment");
								var course_content=$("#course_comment_content");
								console.log(comments);
							   	for(var i=0;i<comments.length;i++){
							   	  var comment=comments[i];
							  	  root.append(course_content.html());
							  	  var body=root.children(":last");
							  	//  alert(root.children(":nth-child(1)").html());
							  	  //alert(body.html());
							  		body.find(".media-heading").html(comment.author_name);
							  		body.find("#commentMain").html(comment.comment_content);
							  	 	body.find("#commentTime").html(comment.comment_time);
							  	 	body.append("<hr>");
							   	}
							 	 
							}
						});
					}
					$(document).ready(function(){
						loadComment();
					});
				  </script>
			</div>
			<div class="col-md-3">
				<h2>${course_name}</h2>
				<h4>作业</h4>
				<hr>
				<ul style="list-style: none;text-align: center;margin:0px;padding: 0px;">
					<li><a href="https://www.baidu.com/">本课程资源</a></li>
					<hr>
					<li ><a id="course_homework" href="#">本课程作业</a></li>
				</ul>

				<h4 style="margin-top: 20px;">课程操作</h4>
				<hr>
				<ul style="list-style: none;text-align: center;margin:0px;padding: 0px;">
					<li id="isCollect">
						<%String flag=(String)session.getAttribute("status");%>
							<%if("1".equals(flag)){ %>
							<div id="cancel">
								<button class="btn btn-danger">
								<i class="fa-star fa"><%=session.getAttribute("amount")%></i> 
								取消收藏
								</button>
							</div>
							<%}else if("0".equals(flag)){ %>
							<div id="collect">
								<button class="btn btn-success">
								<i class="fa-star-o fa"><%=session.getAttribute("amount")%></i> 
								收藏本课程
								</button>
							</div>
							<%} %>
							
					</li>
					<script>
					$(document).ready(function(){
						$.ajax({
							cahe:true,
							type:"POST",
							url:"<c:url value='/CourseServlet?method=loadCourseByCourseId'/>",
							async:false,
							data:{course_id:"<%=course_id%>"},
							error: function(request) {
							     alert("Connection error");
							   },
						   success: function(data) {
								var course=eval("("+data+")");
								 console.warn(course);
							  	$("#course_homework").attr("href",course.homework_url);
							}
						})
					});
					
					var fn1=function(){
						 $.post("<c:url value='/StudentServlet?method=collect'/>", { "course": "gw", "coursename": "数据结构" },
							function (data) {
								alert(data);
							    var jsonData=eval("("+data+")");
							    alert(jsonData.status);
								if(jsonData.status==1){
									var parent_a=$('<button></button>');
									parent_a.addClass('btn btn-danger');
									var child_i=$('<i></i>');
									 parent_a.attr('id','cancel');
									 child_i.addClass('fa-star fa');
									 child_i.append(jsonData.amount);
									 parent_a.append(child_i);
									 $("#isCollect").empty();
									 $("#isCollect").append(parent_a);
									 parent_a.append("<spand>取消收藏</spand>");
									 $("#cancel").bind("click").click(fn2); 
								}else{
										alert(data);
									}
							    }
						);
					};
					var fn2=function(){
						 $.post("<c:url value='/StudentServlet?method=cancel'/>", { "course": "gw", "coursename": "数据结构" },
							function (data) {
								 alert(data);
								 var jsonData=eval("("+data+")");
								 alert(jsonData);
								alert(jsonData.status);
								if(jsonData.status==0){
									 var parent_a=$('<button></button>');
									 parent_a.addClass('btn btn-success');
								 	 var child_i=$('<i></i>');
									 parent_a.attr('id','collect');
									 child_i.addClass('fa-star-o fa');
									 child_i.append(jsonData.amount);
									 parent_a.append(child_i);
									 $("#isCollect").empty();
									 $("#isCollect").append(parent_a);
									 parent_a.append("<spand>收藏本课程</spand>");
									 $("#collect").bind("click").click(fn1); 
								}else{
										alert(data);
									}
							    }
						);
					};
					$(document).ready(function(){
						  $("#collect").click(fn1);
						  $("#cancel").click(fn2);
					})
					
					</script>
					<hr>
					<li><a href=""><i class="fa fa-thumbs-o-up">123</i> 推荐此课程</a></li>
				</ul>
				<h4 style="margin-top: 20px;">课程简介</h4>
				<hr>
				<span><%=course_content%></span>
			</div>
		</div>
		<!--评论区-->

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
