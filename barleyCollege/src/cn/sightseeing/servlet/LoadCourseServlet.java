package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;
import cn.sightseeing.service.CourseService;
import cn.sightseeing.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LoadCourseServlet extends HttpServlet {
	private CourseService courseService= new CourseService();
	private UserService userService=new UserService();
	private User user;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("loadCourseServlet工作开始==========");
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		System.out.println(username);
		if(!username.equals(null)){
			user=userService.loadByUserName(username);
			System.out.println(user);
			String userId=user.getId();
			List<Course> courses=courseService.loadCourse(userId);
			JSONArray jo=JSONArray.fromObject(courses);
			System.out.println(jo);
			session.setAttribute("courses", courses);
			response.getWriter().print(jo.toString());
			session.setAttribute("active", "active");
		}
		System.out.println("loadCourseServlet工作结束==========");
		//request.getRequestDispatcher("/test.jsp").forward(request, response);
	}

}
