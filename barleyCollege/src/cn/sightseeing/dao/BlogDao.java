package cn.sightseeing.dao;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sightseeing.domain.Blog;
import cn.sightseeing.domain.BlogComment;
import cn.sightseeing.domain.Course;
import cn.sightseeing.utils.JdbcUtils;

public class BlogDao {
	private QueryRunner qr = new TxQueryRunner(JdbcUtils.getDataSource());
	
	public List<Blog> loadBlogById(String user_id) throws ServerException{
		String sql="SELECT *FROM blog WHERE user_id=?";
		try {
			return qr.query(sql, new BeanListHandler<Blog>(Blog.class),Integer.parseInt(user_id));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客表失败");
		}
	}
	public List<Blog> loadBlog() throws ServerException{
		String sql="SELECT *FROM blog order by update_time desc";
		try {
			return qr.query(sql, new BeanListHandler<Blog>(Blog.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客表失败");
		}
	}
	public Boolean createdBlog(String user_id,String title,String text_content) throws ServerException{
		String sql="INSERT INTO blog(user_id,title,text_content,update_time,created_time) VALUES(?,?,?,?,?)";
		long l = System.currentTimeMillis();
		Date time=new Date(l);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(time));
		try {
			qr.update(sql,Integer.parseInt(user_id),title,text_content,sdf.format(time),sdf.format(time));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客表失败");
		}
	}
	public Boolean updateBlog(String user_id,String title,String text_content) throws ServerException{
		String sql="UPDATE blog SET title=? and text_content=? and update_time=? WHERE user_id=?";
		long l = System.currentTimeMillis();
		Date time=new Date(l);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(time));
		try {
			qr.update(sql,title,text_content,sdf.format(time),Integer.parseInt(user_id));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("更新博客表失败");
		}
	}
	public int queryBlog(String user_id) throws ServerException{
		String sql="SELECT COUNT(*) FROM blog WHERE user_id=?";
		try {
			return ((Number)qr.query(sql, new ScalarHandler(),user_id)).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("查询博客失败！");
		}
	}
	public int queryBlog() throws ServerException {
		String sql="SELECT COUNT(*) FROM blog";
		try {
			return ((Number)qr.query(sql, new ScalarHandler())).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("查询博客失败！");
		}
	}
	public Blog loadBlogByBlogId(String blog_id) throws ServerException {
		String sql="SELECT *FROM blog WHERE id=?";
		try {
			return qr.query(sql, new BeanHandler<Blog>(Blog.class),Integer.parseInt(blog_id));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客表失败");
		}
	}
	
	public List<BlogComment> loadBlogCommentById(String blog_id)  throws ServerException{
		String sql="SELECT *FROM blogcomment WHERE blog_id=? order by time desc";
		try {
			return qr.query(sql, new BeanListHandler<BlogComment>(BlogComment.class),Integer.parseInt(blog_id));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客评论表失败");
		}
	}
	
	public boolean createdBlogComment(String blog_id,String user_id,String text_content) throws ServerException{
		String sql="INSERT INTO blogcomment(blog_id,user_id,text_content,time) VALUES(?,?,?,?)";
		long l = System.currentTimeMillis();
		Date time=new Date(l);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(time));
		try {
			qr.update(sql,Integer.parseInt(blog_id),
					Integer.parseInt(user_id),text_content,sdf.format(time));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServerException("加载博客表失败");
		}
	}
	

}
