package cn.sightseeing.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载驱动");
		//pwdString username="gw";
		//String password="123456";
		String url="jdbc:mysql://localhost:3306/sightseeing?"+"user=gw&password=123456";
		Connection con=DriverManager.getConnection(url);
		System.out.println("成功登陆！");
		Statement stmt=con.createStatement();;
		
		String sql="SELECT * FROM user";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs!=null&&sql!=null){
			while(rs.next()){//得到数据  
	            System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t");  
	        }
		}
	}

}
