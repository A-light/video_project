package cn.sightseeing.service;

import java.rmi.ServerException;
import java.sql.SQLException;

import cn.sightseeing.dao.UserDao;
import cn.sightseeing.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public boolean regist(User user) throws SQLException, ServerException {
		if(userDao.countByUser(user.getUsername())>0){
			return false;
		}else{
			System.out.println(user.getShenfen());
			userDao.add(user);
			return true;
		}
	}
	
	public User login(String username, String password) {
		try {
			return userDao.login(username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public String getNameByUserId(String userId) throws ServerException{
		User user=userDao.loadUserById(userId);
		return user.getUsername();
	}
	public User loadByUserName(String username) throws ServerException{
		return userDao.loadByUsername(username);
	}
	
}
