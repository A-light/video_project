package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import cn.sightseeing.dao.VideoDao;
import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;
import cn.sightseeing.domain.Video;
import cn.sightseeing.domain.VideoComment;
import cn.sightseeing.service.CourseService;
import cn.sightseeing.service.UserService;
import cn.sightseeing.service.VideoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VideoServlet extends BaseServlet {
	private VideoService videoService=new VideoService();
	private CourseService courseService;
	private HttpSession session;
	private UserService userService=new UserService();
	private String username;
	private String course_name;
	private String course_id;
	private String user_id;
	private JSONObject jsonMap=new JSONObject();
	private String comment;
	private boolean flag;
	private JSONArray jsonArray;
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String submitComment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		session=request.getSession(false);
		username= request.getParameter("username");
		course_name=request.getParameter("course_name");
		comment=(String)request.getParameter("comment");
		System.out.println("用户名："+username+"课程名："+course_name+"评论的内容："+comment);
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			user_id= user.getId();
			course_id=videoService.getCourseId(course_name);
			System.out.println(course_id);
			flag=videoService.submitComment(user_id, course_id, comment,username);
			System.out.println("flag:"+flag);
			if(flag){
				System.out.println("评论提交成功！");
				response.getWriter().print("提交成功！");
			}else{
				response.getWriter().print("提交失败！");
				System.out.println("失败！");
			}
			
		}
		System.out.println("评论处理结束");
		return null;
	}
	/**
	 * 加载评论
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadComment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String course_id=request.getParameter("course_id");
		if(course_id!=null){
			List<VideoComment> comments=videoService.loadComment(course_id);
			//session.setAttribute("comments", comments);
			jsonArray=JSONArray.fromObject(comments);
			System.out.println(jsonArray);
			response.getWriter().print(jsonArray);
		}
		return null;
	}

   public String queryPlaytime(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		username=(String) request.getParameter("username");
		course_name=(String)request.getParameter("course_name");
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			user_id=user.getId();
			course_id=videoService.getCourseId(course_name);
			Cookie[] cookies=request.getCookies();
			String str=username+"_"+course_id;
			System.out.println(str);
			if(cookies!=null){
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					flag=false;
					if (cookie.getName().equals(str)) {
						flag=true;
						if(videoService.queryProgress(user_id, course_id)>0){
							double db_time=videoService.loadaytime(user_id, course_id);
							double play_time=Double.parseDouble(cookie.getValue());
							if(play_time>db_time){
								flag=videoService.updateProgress(play_time, user_id, course_id);
								response.getWriter().print(play_time);
							}else{
								cookie.setValue(Double.toString(db_time));
								System.out.println(cookie.getValue());
								response.getWriter().print(db_time);
							}
							
						}else{
							double play_time=Double.parseDouble(cookie.getValue());
							videoService.setProgress(play_time, user_id, course_id);
						}
					}
				}
				if(!flag){
					Cookie new_cookie=new Cookie(str,"0");
					response.addCookie(new_cookie);
				}
			}else{
				System.out.println("cookie为空");
			}
		}
		return null;
   }
   public String accomplish(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		username=(String) request.getParameter("username");
		course_name=(String)request.getParameter("course_name");
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			user_id=user.getId();
			course_id=videoService.getCourseId(user_id, course_name);
			flag=videoService.accomplish(user_id, course_id);
			if(flag){
				System.out.println("视频完成");
				videoService.updateProgress(0.0, user_id, course_id);
				response.getWriter().print(0);
			}else{
				System.out.println("出错");
			}
		}
		return null;
   }
}
