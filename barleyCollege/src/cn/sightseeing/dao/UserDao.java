package cn.sightseeing.dao;

import java.rmi.ServerException;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sightseeing.domain.Student;
import cn.sightseeing.domain.Teacher;
import cn.sightseeing.domain.User;
import cn.sightseeing.utils.JdbcUtils;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner(JdbcUtils.getDataSource());

	/**
	 * 根据·昵称·查询用户数量
	 * 
	 * @param username
	 * @return
	 * @throws ServerException
	 */
	public int countByUser(String username) throws ServerException {
		String sql = "SELECT COUNT(*) FROM user WHERE username=?";
		try {
			return ((Number)qr.query(sql, new ScalarHandler(), username)).intValue();
		} catch (SQLException e) {
			throw new ServerException("根据用户名查询用户数量失败");
		}
	}
	
	/**
	 * 该处用索引用户
	 * @param username
	 * @return
	 * @throws ServerException
	 */
	public User loadByUsername(String username) throws ServerException {
		String sql = "SELECT * FROM user WHERE username=?";
		try {
			System.out.println(qr.query(sql, new BeanHandler<User>(User.class),username));
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载用户失败");
		}
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws ServerException
	 */
	public User loadUserById(String userId) throws ServerException{
		String sql="SELECT *FROM user WHERE id=?";
		try {
			User user=qr.query(sql, new BeanHandler<User>(User.class),Integer.parseInt(userId));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载用户失败");
		}
	}

	/*
	 * 添加用户 | id | username | password | email | name | age | gender | idcard |
	 * city | school | shenfen | xueli | pic |
	 */
	/**
	 * 该出用来添加新用户
	 * @param t
	 */
	public void addAll(User t) {
		String sql = "insert into user(username,password,email,name,age,gender,idcard,shenfen,xueli,pic)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {  t.getUsername(), t.getPassword(), t.getEmail(), t.getName(), t.getAge(),
					t.getGender(), t.getIdcard(), t.getCity(), t.getSchool(), t.getShenfen(), t.getPic()
				};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 这里用在注册页面
	 * @param user
	 * @throws SQLException
	 */
	public void add(User user) throws SQLException {
		String sql = "insert into user(username,shenfen,email,password) values(?,?,?,?)";
		qr.update(sql, user.getUsername(), user.getShenfen(),user.getEmail(), user.getPassword());
	}
	/**
	 * 该处用来登录
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User login(String username, String password) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username, password);
	}
}
