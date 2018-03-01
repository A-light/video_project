package cn.sightseeing.service;

import cn.sightseeing.dao.UserDao;
import cn.sightseeing.domain.Student;
import cn.sightseeing.domain.Teacher;

public class RegistService {	
	private UserDao userDao=new UserDao();
	
	/**
	 * 查询用户是否已存在, 存在返回true,不存在返回false
	 * 
	 * @param fieldValue
	 * @param fieldType
	 * @return
	 */
	public boolean isUserExists(String fieldValue, byte fieldType) {
		
		return false;
	}

	/**
	 * 如果返回的验证码个数不为零，即存在此验证码，此时判定输入正确
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean isVerifyCodeRight(String phone, String code) {
		
		return false;
	}

	/**
	 * 发送并返回此验证码
	 * 
	 * @param phone
	 * @return
	 */
	private String sendCodeWith(String phone) {
		int code = (int) (Math.random() * 900000 + 100000);
		return String.valueOf(code);
	}

	/**
	 * 根据手机号发送验证码
	 * 
	 * @param phone
	 */
	public void sendVerifyCode(String phone) {
		
	}

	/**
	 * 学生类型注册服务
	 * 
	 * @param user
	 * @param student
	 * @return
	 * @throws ServerException
	 * @throws RegistException
	 */
	
}	
