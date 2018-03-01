 package cn.sightseeing.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sightseeing.domain.User;
import cn.sightseeing.service.UserService;
import cn.sightseeing.utils.CommonUtils;

public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println("调用了login");
		User user = userService.login(username, password);
	//	System.out.println("调用了service");
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("shenfen", user.getShenfen());
			request.setAttribute("msg", "登录成功");
			System.out.println(user.getShenfen());
			List<String> links = new ArrayList<String>();
			System.out.println(request.getHeader("Referer"));
			String url=request.getHeader("Referer");
			int index=url.lastIndexOf("/");
			url=url.substring(index);
			System.out.println(url);
			if("老师".equals(user.getShenfen())){
				return "f:/teacher.jsp";
				//links.add("<a href='" + request.getContextPath() + "/teacher.jsp" + "'>教师主页</a>");
			}else if("学生".equals(user.getShenfen())){
				return "f:/student.jsp";
				//links.add("<a href='" + request.getContextPath() + "/student.jsp" + "'>学生主页</a>");
			}else{
				//links.add("<a href='" + request.getContextPath() + "/index.jsp" + "'>大麦学院主页</a>");
			}
			request.setAttribute("links", links);
			return "f:/msg.jsp";
		} else {
			request.setAttribute("msg", "用户名或密码错误！");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath() + "/index.jsp" + "'>主页</a>");
			request.setAttribute("links", links);
			return "f:/index.jsp";
		}
	}
	
	
	
	public String registPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "f:/regist.jsp";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		String shenfen=(String) request.getParameter("signIn_shen");
		user.setShenfen(shenfen);
		System.out.println(user.getUsername());
		System.out.println(user.getShenfen());
		boolean flag=userService.regist(user);
		if(flag){
			request.setAttribute("msg", "注册成功");
		}else{
			request.setAttribute("msg", "用户名已存在，重新注册");
		}
		List<String> links = new ArrayList<String>();
		links.add("<a href='UserServlet?method=loginPre'>登录页面</a>");
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		request.setAttribute("links", links);
		return "f:/index.jsp";
	}
	
	public String loginPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "f:/index.jsp";
	}
	
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username == null) {
			request.setAttribute("msg", "您还没有登录，不能退出！");
		} else {
			Enumeration em = request.getSession().getAttributeNames();
			 while(em.hasMoreElements()){
			  request.getSession().removeAttribute(em.nextElement().toString());
			 }
			request.setAttribute("msg", "您已退出！");
		}
		List<String> links = new ArrayList<String>();
	//	links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		//request.setAttribute("links", links);
		return "f:/index.jsp";
	}
	
}

