package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gw
 *
 */
public class LoginServlet extends HttpServlet {
	
	public void init() throws ServletException {
		System.out.println("我来了");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello Ajax！！");
		// response.getWriter().print("Hello AJAX!!!");
		response.setContentType("text/html;charset=utf-8");
		String str = "{\"name\":\"张三\", \"age\":18, \"sex\":\"male\"}";
		response.getWriter().print(str);
		System.out.println(str);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");// 获取请求参数
		System.out.println("(POST：) Hello AJAX!" + username);
		response.getWriter().print("(POST：) Hello AJAX!!!" + username);
	}

}
