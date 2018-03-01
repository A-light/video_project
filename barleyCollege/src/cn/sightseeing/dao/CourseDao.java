package cn.sightseeing.dao;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;
import cn.sightseeing.domain.Video;
import cn.sightseeing.domain.VideoComment;
import cn.sightseeing.utils.JdbcUtils;

/**
 * 此处暂时以名字为主键
 * @author gw
 *
 */
public class CourseDao {
	private QueryRunner qr = new TxQueryRunner(JdbcUtils.getDataSource());

	/**
	 * 查询课程数量
	 * 
	 * @param courseName
	 * @return
	 * @throws ServerException
	 */
	public int countByCourseName(String courseName) throws ServerException {
		String sql = "SELECT COUNT(*) FROM course WHERE course_name=?";
		try {
			return ((Number) qr.query(sql, new ScalarHandler(), courseName)).intValue();
		} catch (SQLException e) {
			throw new ServerException("根据课程名查询课程数量失败");
		}
	}

	/**
	 * 这里添加课程
	 * @param course
	 * @throws SQLException
	 */
	public void addCourse(Course course) throws SQLException {
		// id | url | user_id | created_time |
		// ending_time | course_content | name | type_of | notice_content
		String sql = "insert into Course(course_name,course_content,video_url,homework_url,user_id,created_time,ending_time,type_of,status) values(?,?,?,?,?,?,?,?,?)";
		qr.update(sql, course.getCourse_name(),course.getCourse_content(), course.getVideo_url(),course.getHomework_url(),
			course.getUser_id(), course.getCreated_time(), course.getEnding_time(),course.getType_of(),"1");
	}
	
	/**
	 * 该字段用来更新用户状态，暂时无法实现自动更新，必须由教师更新。
	 * 此处暂时用于取消课程
	 * @param courseName
	 * @throws SQLException
	 */
	public void uppdateCourseStatus(String courseName) throws SQLException{
		String sql="updata Course as c set c.status=0  where name=?";
		qr.update(sql,courseName);
	}
	
	
	/**
	 * @param userId
	 * @return
	 * @throws ServerException
	 */
	public List<Course> loadByUserId(String userId) throws ServerException{
		int id=Integer.parseInt(userId);
		String sql="SELECT *FROM course WHERE user_id=? order by created_time desc";
		System.out.println(id);
		try {
			List<Course> list=qr.query(sql,
					new BeanListHandler<Course>(Course.class),id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载视频失败");
		}
	}
	
	public List<Course> loadCourse() throws ServerException{
		String sql="SELECT *FROM course ORDER BY created_time DESC";
		//System.out.println("******");
		try {
			List<Course> list=qr.query(sql,
					new BeanListHandler<Course>(Course.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载视频失败");
		}
	}
	public List<Course> loadByCourseType(String type_of) throws ServerException{
		String sql="SELECT *FROM course WHERE type_of=?";
		try {
			List<Course> list=qr.query(sql,
					new BeanListHandler<Course>(Course.class),type_of);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载视频失败");
		}
	}
	
	/**
	 * @param user_id
	 * @param course_id
	 * @param comment_content
	 * @param author_name
	 * @return
	 * @throws ServerException
	 */
	public boolean submitComment(String user_id,String course_id,String comment_content,
			String author_name)throws ServerException{
		String sql="INSERT INTO videocomment(user_id,course_id,comment_content,"
				+ "author_name,comment_time) VALUES(?,?,?,?,?)";
		long l = System.currentTimeMillis();
		Date time=new Date(l);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(time));
		try{
			qr.update(sql,Integer.parseInt(user_id),Integer.parseInt(course_id),comment_content,author_name,sdf.format(time));
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("评论提交失败！");
		}
	}
	
	public List<VideoComment> loadComment(String course_id) throws ServerException {
		String sql="SELECT *FROM videocomment WHERE course_id=? order by comment_time desc";
		try {
			return qr.query(sql,
					new BeanListHandler<VideoComment>(VideoComment.class),Integer.parseInt(course_id));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("评论课程评论失败！");
		}
	}
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public String getCourseId(String user_id,String course_name) throws ServerException {
		String sql="SELECT *FROM course WHERE user_id=? and course_name=?";
		try{
			Course course=qr.query(sql,
					new BeanHandler<Course>(Course.class),Integer.parseInt(user_id),course_name);
			return course.getId().toString();
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("评论课程ID失败！");
		}
	}
	/**
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public String getCourseId(String course_name) throws ServerException {
		String sql="SELECT *FROM course WHERE course_name=?";
		try{
			Course course=qr.query(sql,
					new BeanHandler<Course>(Course.class),course_name);
			return course.getId().toString();
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("评论课程ID失败！");
		}
	}
	
	public String getCourseNamebyId(String course_id) throws ServerException {
		String sql="SELECT *FROM course WHERE id=?";
		try{
			Course course=qr.query(sql,
					new BeanHandler<Course>(Course.class),Integer.parseInt(course_id));
			return course.getCourse_name();
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("评论课程ID失败！");
		}
	}

	public Course loadCourseByCourseId(String course_id) throws ServerException {
		String sql="SELECT *FROM course WHERE id=?";
		try{
			Course course=qr.query(sql,
					new BeanHandler<Course>(Course.class),Integer.parseInt(course_id));
			return course;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServerException("评论课程ID失败！");
		}
	}
}
