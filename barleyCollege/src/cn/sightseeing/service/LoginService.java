package cn.sightseeing.service;

import java.rmi.ServerException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sightseeing.dao.UserDao;
import cn.sightseeing.domain.User;

public class LoginService extends HttpServlet {
	private final UserDao userDao = new UserDao();
	public static final String ERR_USERLOGIN = "用户名或密码错误";
	/**
	 * 用户登录方法
	 * <p>
	 * 1  尝试以账户名登录；；
	 * 3  失败则用户不存在，否则验证登录密码。
	 * 4  根据用户类型加载相应角色
	 * </p>
	 * 
	 * @param user
	 * @return
	 * @throws UserLoginException
	 * @throws ServerException
	 */
	public User login(User form) throws UserLoginException, ServerException {
		User user = userDao.loadByUsername(form.getUsername());
		if (user == null)
			throw new UserLoginException(ERR_USERLOGIN);
		if (form.getPassword() == null || "".equals(form.getPassword().trim())
				|| !user.getPassword().equals(form.getPassword()))
			throw new UserLoginException(ERR_USERLOGIN);
		switch (user.getShenfen()) {
		case "学生":
			user = userDao.loadByUsername(user.getUsername());
			break;
		case "老师":
			user = userDao.loadByUsername(user.getUsername());
			break;
		default:
			break;
		}
		return user;
	}
	
}
