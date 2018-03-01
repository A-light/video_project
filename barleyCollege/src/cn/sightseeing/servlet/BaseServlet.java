package cn.sightseeing.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 得到方法名称是否可以通过反射来调用方法
		 */
		String methodName = request.getParameter("method");
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("你没有传递参数！");
		}
		Class c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("你调用的方法" + methodName + "不存在");
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		/*
		 * 调用method方法
		 */
		/*
		 * try{ method.invoke(this,request, response); }catch(Exception e){
		 * System.out.println("您调用的方法："+methodName+"在内部发生了异常！！"); throw new
		 * RuntimeException(e); }
		 */
		try {
			String result = (String) method.invoke(this, request, response);
			if (result == null || result.trim().isEmpty()) {
				return;
			} else if (result.contains(":")) {
				int index = result.indexOf(":");
				String head = result.substring(0, index);
				String path = result.substring(index + 1);
				
				if (head.startsWith("r")) {
					response.sendRedirect(request.getContextPath() + path);
				} else if (head.startsWith("f")) {
					request.getRequestDispatcher(path).forward(request, response);
				} else {
					throw new RuntimeException("你指定的操作：" + head + "当前版本还不支持");
				}
			} else {
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("您调用的方法：" + methodName + "在内部发生了异常！！");
			throw new RuntimeException(e);
		}

	}
}
