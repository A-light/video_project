package cn.sightseeing.dao;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sightseeing.domain.Collection;
import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.Progress;
import cn.sightseeing.domain.User;
import cn.sightseeing.utils.JdbcUtils;

/**
 * 用于视频处理的一些信息
 * @author gw
 *
 */
public class VideoDao {
	private QueryRunner qr = new TxQueryRunner(JdbcUtils.getDataSource());
	
	/**
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public Course loadVideo(String course_name) throws ServerException{
		String sql="SELECT *FROM course WHERE course_name=?";
		try {
			return qr.query(sql, new BeanHandler<Course>(Course.class),course_name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载课程失败");
		}
	}
	/**
	 * @return
	 * @throws ServerException
	 */
	public List<Course> loadVideo() throws ServerException{
		try {
			String sql="SELECT *FROM course";
			List<Course> list=qr.query(sql,
					new BeanListHandler<Course>(Course.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载视频失败");
		}
	}
	
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException 
	 */
	public boolean collectVideo(String user_id,String course_name) throws ServerException{
		String sql="insert into collection(user_id,course_name,status) values(?,?,?)";
		try {
			qr.update(sql,user_id,course_name,"1");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("收藏视频失败");
		}
	}
	
	/**
	 * 设置收藏表中status为0
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public boolean setStatus(String status,String user_id,String course_name) throws ServerException{
		String sql="update collection set status=? where user_id=? and course_name=?";
		try {
			qr.update(sql,status,user_id,course_name);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("取消视频失败");
		}
	}

	/**
	 * @param user_id
	 * @return
	 * @throws ServerException
	 */
	public List<Course> loadCollection(String user_id) throws ServerException{
		String sql="select c.id,c.video_url,c.homework_url,c.cuser_id,c.created_time,c.ending_time,c.course_content,c.course_name,c.type_of,c.notice_content,c.video_id ,c.status "
				+ "	from course c,collection d where d.course_name=c.course_name and d.user_id=? and d.status=?";
		try {
			return (List<Course>) qr.query(sql, new BeanListHandler<Course>(Course.class),Integer.parseInt(user_id),"1");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("学生收藏的课程加载失败");
		}
	}
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public Collection loadCollection(String user_id,String course_name) throws ServerException{
		String sql="SELECT *FROM collection WHERE user_id=? and course_name=?" ;
		try{
			return  qr.query(sql, new BeanHandler<Collection>(Collection.class),Integer.parseInt(user_id),course_name);
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("课程收藏表加载失败");
		}
	}
	
	/**
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public int countByCourseName(String course_name) throws ServerException{
		String sql="SELECT COUNT(*) FROM collection WHERE course_name=? and status=?";
		try {
			return ((Number)qr.query(sql, new ScalarHandler(), course_name,"1")).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("出现收藏表数量失败！");
		}
	}
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public int countByIdAndName(String user_id,String course_name) throws ServerException{
		String sql="SELECT COUNT(*) FROM collection WHERE user_id=? and course_name=?";
		try {
			return ((Number)qr.query(sql, new ScalarHandler(), user_id,course_name)).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("出现收藏表数量失败！");
		}
	}
	
	/**
	 * @param user_id
	 * @param course_id
	 * @return
	 * @throws ServerException
	 */
	public Progress loadProgressById(String user_id,String course_id) throws ServerException{
		String sql="SELECT * FROM progress WHERE user_id=? and course_id=?";
		try{
			return  qr.query(sql,  new BeanHandler<Progress>(Progress.class),user_id,course_id);
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("加载课程失败！");
		}
	}
	
	public List<Progress> loadProgressById(String course_id) throws ServerException{
		String sql="SELECT * FROM progress WHERE course_id=?";
		try{
			return  qr.query(sql,new BeanListHandler<Progress>(Progress.class),course_id);
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("加载课程失败！");
		}
	}
	/**
	 * @param playtime
	 * @param user_id
	 * @param course_id
	 * @return
	 * @throws ServerException
	 */
	public Boolean setProgress(double playtime,String user_id,String course_id) throws ServerException{
		String sql="INSERT INTO progress(playtime,user_id,course_id) VALUES(?,?,?)";
		try{
			qr.update(sql,playtime,user_id,course_id);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("初始化失败！");
		}
	}
	/**
	 * @param playtime
	 * @param user_id
	 * @param course_id
	 * @return
	 * @throws ServerException
	 */
	public Boolean updateProgress(double playtime,String user_id,String course_id) throws ServerException{
		String sql="UPDATE progress  SET playtime=? WHERE user_id=? and course_id=?";
		try{
			qr.update(sql,playtime,user_id,course_id);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("更新失败失败！");
		}
	}
	/**
	 * @param user_id
	 * @param course_id
	 * @return
	 * @throws ServerException
	 */
	public int queryProgress(String user_id,String course_id) throws ServerException{
		String sql="SELECT COUNT(*) FROM progress WHERE user_id=? and course_id=?";
		try {
			return ((Number)qr.query(sql, new ScalarHandler(), user_id,course_id)).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("进度查询失败！");
		}
	}
	public boolean setProgressStatus(String status,String user_id,String course_id) throws ServerException{
		String sql="UPDATE progress  SET status=? WHERE user_id=? and course_id=?";
		try{
			qr.update(sql,status,user_id,course_id);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("progress状态更新失败！");
		}
	}
}
