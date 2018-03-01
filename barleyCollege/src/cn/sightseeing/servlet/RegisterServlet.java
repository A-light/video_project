package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sightseeing.domain.Teacher;
import cn.sightseeing.service.RegistService;
import cn.sightseeing.utils.CommonUtils;

public class RegisterServlet extends BaseServlet {
	/*
	 * 注册新用户
	 */
	private RegistService registService = new RegistService();

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1、封装表单数据regist对象
		 * 2、补全：id，使用uuid
		 * 3、使用service方法完成添加工作
		 * 4、向request域保存成功信息
		 * 5、转发到msg.jsp或者弹框
		 */
		Teacher t=CommonUtils.toBean(request.getParameterMap(), Teacher.class);
	}
}