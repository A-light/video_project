package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPasswordServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String password=request.getParameter("password");
		String confirm_password=request.getParameter("confirm_password");
		System.out.println(password+" "+confirm_password);
		if(password.equals(confirm_password)){
			request.setAttribute("msg", "");
		}else{
			request.setAttribute("msg", "两次密码输入不一致！");
		}
	}

}
