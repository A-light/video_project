package cn.sightseeing.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sightseeing.domain.Blog;
import cn.sightseeing.domain.BlogComment;
import cn.sightseeing.domain.User;
import cn.sightseeing.service.BlogService;
import cn.sightseeing.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BlogServlet extends BaseServlet {
	BlogService blogService =new BlogService();
	UserService userService=new UserService();
	public String doBlog(HttpServletRequest request,HttpServletResponse response) throws ServerException, SQLException{ 
		String title=request.getParameter("title");
		String text_content=request.getParameter("text_content");
		String username=request.getParameter("username");
		if(title!=null&&text_content!=null&&username!=null){
			User user=userService.loadByUserName(username);
			String user_id=user.getId();
			boolean flag=blogService.createdBlog(user_id, title, text_content);
			if(flag){
				System.out.println("成功！");
			}else{
				System.out.println("失败！");
			}
		}
		return "r:/blog.jsp";
	}
	public String loadBlog(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(blogService.queryBlog()>0){
			List<Blog> blogs=blogService.loadBlog();
			JSONArray list= new JSONArray();
			int i=0;
			for(Blog blog:blogs){
				JSONObject map=new JSONObject();
				String username=userService.getNameByUserId(blog.getUser_id());
				map.put("blog_id", blog.getId());
				map.put("user_id", blog.getUser_id()); 
				map.put("blog_username", username);
				map.put("blog_title",blog.getTitle());
				map.put("blog_created_time", blog.getCreated_time());
				list.add(map);
			}
			
			response.getWriter().print(list);;
			System.out.println(list);
		}
		return null;
	}
	public String doBlogContent(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		response.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String blog_id=request.getParameter("blog_id");
		System.out.println("user:"+user_id+"blog:"+blog_id);
		if(user_id!=null&&blog_id!=null){
			Blog blog=blogService.loadBlogByBlogId(blog_id);
			JSONObject map=new JSONObject();
			String username=userService.getNameByUserId(blog.getUser_id());
			map.put("blog_username", username);
			map.put("blog_title",blog.getTitle());
			map.put("blog_textcontent", blog.getText_content()); 
			map.put("blog_updated_time", blog.getUpdate_time());
			response.getWriter().print(map);
		}
		return null;
	}
	public String loadBlogComment(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		response.setCharacterEncoding("utf-8");
		String blog_id=request.getParameter("blog_id");
		if(blog_id!=null){
			 List<BlogComment> blogComments=blogService.loadBlogCommentById(blog_id);
			 JSONArray list=new JSONArray();
			 System.out.println(blogComments);
			 for(BlogComment blogComment:blogComments){
				 JSONObject map=new JSONObject();
				 map.put("comment_username", userService.getNameByUserId(blogComment.getUser_id()));
				 map.put("comment_content", blogComment.getText_content());
				 map.put("comment_time", blogComment.getTime());
				 list.add(map);
			 }
			 System.out.println(list);
			 response.getWriter().print(list);
		}
		
		return null;
	}
	public String doBlogComment(HttpServletRequest request,HttpServletResponse response) throws IOException { 
		response.setCharacterEncoding("utf-8");
		String blog_id=request.getParameter("blog_id");
		String username=request.getParameter("username");
		String text_content=request.getParameter("input_comment");
		System.out.println(blog_id+" "+username+" "+text_content);
		if(blog_id!=null&&username!=null&&text_content!=null){
			User user=userService.loadByUserName(username);
			String user_id=user.getId();
			
			boolean flag=blogService.createdBlogComment(blog_id,user_id,text_content);
			if(flag){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
		}
		return null;
	}
}
