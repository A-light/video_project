package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import cn.sightseeing.utils.CommonUtils;

public class CollectServlet extends HttpServlet {
	private CollectionService cs=new CollectionService();
	private UserService userService=new UserService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String course_name=(String)session.getAttribute("course_name");
		System.out.println(username+course_name);
		if(username!=null){
			User user=userService.loadByUserName(username);
			String user_id=user.getId();
			System.out.println(user_id);
			boolean isCreate=cs.collectVideo(user_id, course_name);
			if(isCreate){
				System.out.println("收藏成功");
			}else{
				System.out.println("收藏失败");
			}
			/*Map map=new HashMap<String,String>();
			map.put("user_id", user.getId());
			map.put("course_name",course_name);
			Collection collection = CommonUtils.toBean(map, Collection.class);
			*/
		}
	}

}
