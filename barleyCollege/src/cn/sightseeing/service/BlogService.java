package cn.sightseeing.service;

import java.rmi.ServerException;
import java.util.List;

import cn.sightseeing.dao.BlogDao;
import cn.sightseeing.domain.Blog;
import cn.sightseeing.domain.BlogComment;

public class BlogService {
	BlogDao blogDao=new BlogDao();
	
	public List<Blog> loadBlogById(String user_id) throws ServerException{
		return blogDao.loadBlogById(user_id);
	}
	public Blog loadBlogByBlogId(String blog_id) throws ServerException{
		return blogDao.loadBlogByBlogId(blog_id);
	}
	public List<Blog> loadBlog() throws ServerException{
		return blogDao.loadBlog();
	}
	public Boolean createdBlog(String user_id,String title,String text_content) throws ServerException{
		return blogDao.createdBlog(user_id, title, text_content);
	}
	public Boolean updateBlog(String user_id,String title,String text_content) throws ServerException{
		return blogDao.updateBlog(user_id, title, text_content);
	}
	public int queryBlog(String user_id) throws ServerException{
		return blogDao.queryBlog(user_id);
	}
	public int queryBlog() throws ServerException{
		return blogDao.queryBlog();
	}
	public List<BlogComment> loadBlogCommentById(String blog_id)  throws ServerException{
		return blogDao.loadBlogCommentById(blog_id);
	}
	public boolean createdBlogComment(String blog_id, String user_id, String text_content) throws ServerException {
		return blogDao.createdBlogComment(blog_id,user_id,text_content);
	}
}