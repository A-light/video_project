package cn.sightseeing.servlet;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;
import cn.sightseeing.service.CourseService;
import cn.sightseeing.service.UserService;
import cn.sightseeing.utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class CourseServlet extends BaseServlet {
	private CourseService courseService= new CourseService();
	private UserService userService=new UserService();
	private JSONArray jsonCourses;

	public String createCourse(HttpServletRequest request,HttpServletResponse response) throws ServerException, SQLException{
		HttpSession session=request.getSession(false);
//		String str=request.getParameterMap().toString();
		System.out.println("CourseServlet c任务开始============");
		Map map=(Map) session.getAttribute("map");
		String username=(String) session.getAttribute("username");
		session.setAttribute("username",username);
		System.out.println(map.toString());
		Course course = CommonUtils.toBean(map, Course.class);
		System.out.println("CourseServlet c任务结束============");
		if(courseService.createCourse(course)){
			session.setAttribute("msg", "创建成功！");
			return "r:/teacher.jsp";
		}else{
			session.setAttribute("msg", "创建失败！");
			return "r:/teacher.jsp";
		}
		
		
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public String closeCourse(HttpServletRequest request,HttpServletResponse response) throws SQLException{
		String name=request.getParameter("courseName");
		courseService.closeCourse(name);
		return "f:/msg.jsp";
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 * @throws ServerException 
	 */
	public String loadCourse(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, ServerException{
		HttpSession session=request.getSession();
		String username=request.getParameter("username");
		if(username!=null){
			User user=userService.loadByUserName(username);
			String userId=user.getId();
			List<Course>courses=courseService.loadCourse(userId);
			session.setAttribute("courses", courses);
		}
		
		return null;
	}
	public String loadCourseByType(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException{
		response.setCharacterEncoding("utf-8");
		String type_of=request.getParameter("type_of");		
		if("计算机".equals(type_of)){
			List<Course> computer_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(computer_courses);
			response.getWriter().print(jsonCourses.toString());
			System.out.println(jsonCourses);
		}else if("法学".equals(type_of)){
			List<Course> law_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(law_courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}else if("外语".equals(type_of)){
			List<Course> language_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(language_courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}else if("艺术".equals(type_of)){
			List<Course> language_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(language_courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}else if("文学".equals(type_of)){
			List<Course> language_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(language_courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}else if("哲学".equals(type_of)){
			List<Course> language_courses =courseService.loadCourseByType(type_of);
			jsonCourses=JSONArray.fromObject(language_courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}else {
			List<Course> courses=courseService.loadCourse();
			jsonCourses=JSONArray.fromObject(courses);
			response.getWriter().print(jsonCourses);
			System.out.println(jsonCourses);
		}
		return null;
	}
	public String loadCourseByCourseId(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException{
		response.setCharacterEncoding("utf-8");
		String course_id=request.getParameter("course_id");
		System.out.println("course_id"+course_id);
		if(course_id!=null){
			Course course=courseService.loadCourseByCourseId(course_id);
			JSONObject map=JSONObject.fromObject(course);
			response.getWriter().print(map);
			System.out.println(map.toString());
		}
		return null;
	}
}