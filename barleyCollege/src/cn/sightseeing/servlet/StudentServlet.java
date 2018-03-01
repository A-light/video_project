package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sightseeing.domain.Collection;
import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;
import cn.sightseeing.service.CollectionService;
import cn.sightseeing.service.UserService;
import net.sf.json.JSONObject;

public class StudentServlet extends BaseServlet {
	private HttpSession session;
	private CollectionService cs=new CollectionService();
	private UserService userService=new UserService();
	private String username;
	private String course_name;
	private String status;
	private JSONObject jsonMap=new JSONObject();
	
	/**
	 * 这是加载视频方法，加载视频。
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		 session=request.getSession();
		 username=(String) session.getAttribute("username");
		
		if(username!=null){
			User user=userService.loadByUserName(username);
			String user_id= user.getId();
			List<Course> courses=cs.loadCollection(user_id);
			System.out.println(courses.toString());
			session.setAttribute("student_courses",courses);
			session.setAttribute("msg", "视频加载成功");
			response.getWriter().print("视频加载成功");
		}else{
			session.setAttribute("msg", "请登录");
			response.getWriter().print("视频加载失败");
		}
		
		
		return null;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String queryStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		session=request.getSession();
		username=(String) session.getAttribute("username");
		course_name=(String) session.getAttribute("course_name");
		System.out.println(username+course_name);
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			String user_id= user.getId();
			int cnt=cs.countByIdAndName(user_id,course_name);
			if(cnt>0){
				status=cs.queryStatus(user_id, course_name);
			}else{
				status="0";
			}
			int amount=cs.countByCourseName(course_name);
			session.setAttribute("amount", amount);
			jsonMap.put("amount",amount);
			System.out.println(amount+"cnt"+cnt);
			session.setAttribute("status", status);
			jsonMap.put("status",status);
			response.getWriter().print(jsonMap.toString());
		}
		return null;
	}

	/**这是取消视频方法。
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		session=request.getSession();
		course_name=(String) session.getAttribute("username");
		String course_name=(String) session.getAttribute("course_name");
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			String user_id= user.getId();
			int cnt=cs.countByIdAndName(user_id,course_name);
			if(cnt>0){
				status="0";
				boolean s=cs.setStatus(status,user_id, course_name);
				if(s){
					int amount=cs.countByCourseName(course_name);
					System.out.println(amount);
					session.setAttribute("amount", amount);
					jsonMap.put("amount",amount);
 					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频取消成功");
				}else{
					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频取消失败");
				}
			}
			session.setAttribute("status", status);
		}
		return null;
	}
	
	/**
	 * 着是收藏视频方法。
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		course_name=(String) session.getAttribute("username");
		String course_name=(String) session.getAttribute("course_name");
		if(username!=null&&course_name!=null){
			User user=userService.loadByUserName(username);
			String user_id= user.getId();
			int cnt=cs.countByIdAndName(user_id,course_name);
			if(cnt>0){
				status="1";
				boolean s=cs.setStatus(status,user_id, course_name);
				if(s){
					int amount=cs.countByCourseName(course_name);
					System.out.println(amount+"cnt"+cnt);
					session.setAttribute("amount", amount);
					jsonMap.put("amount",amount);
					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频收藏成功");
				}else{
					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频收藏失败");
				}
				
			}else{
				boolean s=cs.collectVideo(user_id, course_name);
				if(s){
					int amount=cs.countByCourseName(course_name);
					System.out.println(amount+"cnt"+cnt);
					session.setAttribute("amount", amount);
					jsonMap.put("amount",amount);
					status="1";
					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频收藏成功");
				}else{
					jsonMap.put("status", status);
					response.getWriter().print(jsonMap.toString());
					System.out.println("视频收藏失败");
				}
			}
			session.setAttribute("status", status);
		}
		return null;
	}
	
}
