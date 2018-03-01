package cn.sightseeing.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

public class JdbcUtils {
	private static DataSource dataSource;

	static {
		init();
	}

	// 每个线程都会自己的一份
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

	/*
	 * 如果有事务，说明connectionHolder中存在Connection对象 这个事务都用这个connection来完成
	 */
	public static void beginTransaction() throws SQLException {
		/*
		 * 如果已经开始了事务，就不能再次开启事务！
		 */
		Connection con = connectionHolder.get();
		if (con != null) {
			throw new SQLException("已经存在了事务，不能再次开启！");
		}
		// 获取连接
		con = dataSource.getConnection();
		// 设置为手动提交
		con.setAutoCommit(false);
		// 放到Holder中
		connectionHolder.set(con);
	}

	/*
	 * 提交事务了 当前事务的连接在holder中 从holder中获取连接，然后提交
	 */
	public static void commitTransaction() throws SQLException {
		Connection con = connectionHolder.get();
		// 如果holder中没有con,说明根本没有开启事务
		if (con == null) {
			throw new SQLException("没有事务可以提交！");
		}
		con.commit();// 提交事务
		con.close();// 关闭连接
		connectionHolder.remove();// 从holder中移除连接对象
	}

	public static void rollbackTransaction() throws SQLException {
		Connection con = connectionHolder.get();
		// 如果holder中没有con,说明根本没有开启事务
		if (con == null) {
			throw new SQLException("没有事务可以回滚！");
		}
		con.rollback();// 回滚事务
		con.close();// 关闭连接
		connectionHolder.remove();// 从holder中移除连接对象
	}

	// 在连接池没有初始化时，去初始化它
	// 然后再返回。
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 给dataSource赋值
	private static void jndi(String jndiName) {
		try {
			Context cxt = new InitialContext();
			dataSource = (DataSource) cxt.lookup("java:/comp/env/" + jndiName);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	// 给dataSource赋值
	private static void initDataSource(Properties prop) {
		/*
		 * 1获取连接池类名称 2通过反射他都连接池对象 3通过BeanUtils把属性注入
		 */
		String dataSourceClassName = prop.getProperty("dataSourceClassName");
		try {
			// 加载连接池的类型
			Class clazz = Class.forName(dataSourceClassName);
			// 通过反射创建连接池对象
			dataSource = (DataSource) clazz.newInstance();
			BeanUtils.populate(dataSource, prop);
		} catch (Exception e) {
			throw new RuntimeException("dataSourceClassName找不到：" + dataSourceClassName, e);
		}
	}

	// 在dataSource没有被初始化时调用它
	private static void init() {
		// 初始化
		/*
		 * 1如果在Tomcat环境下，已经配置JNDI，那么就不用管其他的配置信息了，因为会在Tonmcat中配置好了 2如果用户使用的是DBCP
		 * 3如果用户使用的c3p0
		 */
		/*
		 * 当用户配置了jndiName属性时，那么使用这个值直接调用jndi()方法 indi()方法
		 */
		try {
			// 加载配置文件，配置文件名为dbconfig.properties
			Properties prop = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
			prop.load(in);

			// 获取jndiName
			String jndiName = prop.getProperty("jndiName");

			// 如果存在jndiName，那么就直接使用jndi的初始化方式
			// jndi初始化
			if (jndiName != null) {
				jndi(jndiName);
				return;
			}

			// 配置初始化
			initDataSource(prop);
		} catch (Exception e) {
			throw new RuntimeException("dbconfig.properties找不到：", e);
		}
	}

	/*
	 * 修改了getConnection() 当holder中存在Connection，说明用户已经开启了事务
	 * 那么用来在获取连接时，一定要让他使用事务的连接对象
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = connectionHolder.get();
		if (con != null) {
			return con;
		}
		return dataSource.getConnection();
	}

	public static void close(Connection con, Statement stmt) {
		close(con, stmt, null);
	}

	public static void close(Connection con) {
		close(con, null, null);
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 释放链接
	 * 
	 * @param connection
	 * 
	 * @
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = connectionHolder.get();
		/*
		 * 判断它是不是事物专用，如果是，就不关闭！ 如果不是事物专用，那么就要关闭
		 */
		// 如果con==null，说明现在没有事物，那么connection一定不是事物专用的！
		if (con == null)
			connection.close();
		if (con != connection)
			connection.close();
	}

}
