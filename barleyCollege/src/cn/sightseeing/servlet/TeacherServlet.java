package cn.sightseeing.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.Progress;
import cn.sightseeing.domain.User;
import cn.sightseeing.service.CourseService;
import cn.sightseeing.service.UserService;
import cn.sightseeing.service.VideoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TeacherServlet extends BaseServlet {
	private VideoService videoService=new VideoService();
	private CourseService courseService=new CourseService();
	private UserService userService=new UserService();
	private String username;
	private String course_name;
	
	
	public String supervise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		username =request.getParameter("username");
		if(username!=null){
			User user=(User) userService.loadByUserName(username);
			String user_id=user.getId();
			List<Course> courses=courseService.loadCourse(user_id);
			JSONArray list = new JSONArray();
			for(Course course:courses){
				System.out.println("课程id="+course.getId()+"课程名:"+course.getCourse_name());
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("course_name", course.getCourse_name());
				List<Progress> progresss=videoService.loadProgressById(course.getId());
				map.put("progress", JSONArray.fromObject(progresss));
				list.add(JSONObject.fromObject(map));
			}
			//JSONArray jsonArray = JSONArray.fromObject(map);
			System.out.println(list.toString());
			response.getWriter().print(list);;
		}
		return null;
	}
}
