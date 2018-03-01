package cn.sightseeing.servlet;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.sightseeing.domain.User;
import cn.sightseeing.service.CourseService;
import cn.sightseeing.service.UserService;
import cn.sightseeing.utils.CommonUtils;


public class UploadServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		HttpSession session=request.getSession();
		Map map=new HashMap<String,String>();
		try {
			List<FileItem> list=sfu.parseRequest(request);
			System.out.println("uploadServlet工作开始");
			for(FileItem fileItem : list){
				
				if(fileItem.isFormField()){
					String name = fileItem.getFieldName();
					String value=fileItem.getString("utf-8");
					System.out.println(name+value);
					
					// id | video_url    
					//	| homework_url         
					//| user_id | created_time |
					//ending_time | course_content    
					// | course_name     | type_of | notice_content   
					// | video_id | status |
					
						if(name.equals("courseName")){
							map.put("course_name", value);
							session.setAttribute("courseName", value);
						}else if(name.equals("courseTitle")){
							map.put("courseTitle", value);
							session.setAttribute("courseTitle", value);
						}else if(name.equals("courseContent")){
							map.put("course_content", value);
							session.setAttribute("courseContent", value);
						}else if(name.equals("beginTime")){
							map.put("created_time", value);
							session.setAttribute("beginTime", value);
						}else if(name.equals("endingTime")){
							map.put("ending_time", value);
							session.setAttribute("ending_time", value);
						}else if(name.equals("courseType")){
							map.put("type_of", value);
							session.setAttribute("courseType", value);
						}
				}
				
				
				
				
				
				else{
					System.out.println("文件目录");
					String name = fileItem.getName();//获取上传文件的名称
					System.out.println(name);
					// 如果上传的文件名称为空，即没有指定上传文件
					if(name == null || name.isEmpty()) {
						continue;
					}
					
					// 如果客户端使用的是IE6，那么需要从完整路径中获取文件名称
					int lastIndex = name.lastIndexOf("\\");
					if(lastIndex != -1) {
						name = name.substring(lastIndex + 1);
					}
					int fileType=name.lastIndexOf(".");
					String fileTypeStr=name.substring(fileType);
					String savePath=null;
					String pathName=CommonUtils.uuid()+"_"+name;
					String url=request.getContextPath();
					System.out.println(fileTypeStr);
					if(fileTypeStr.equals(".jpg")||fileTypeStr.equals(".gif")||fileTypeStr.equals(".jpeg")||fileTypeStr.equals(".png")||fileTypeStr.equals(".swf")){
						savePath= this.getServletContext().getRealPath("/files/images");
						url=url+"/files/images/"+pathName;
						map.put("homework_url", url);
						session.setAttribute("homeworkUrl",url);
						session.setAttribute("msg", "上传成功！");
					}else if(fileTypeStr.equals(".avi")||fileTypeStr.equals(".mov")||fileTypeStr.equals(".mp4")||fileTypeStr.equals(".flv")||".swf".equals(fileTypeStr)){
						savePath= this.getServletContext().getRealPath("/files/videos");
						url=url+"/files/videos/"+pathName;
						map.put("video_url", url);
						session.setAttribute("video_url",url);
						session.setAttribute("msg", "上传成功！");
					}else if(fileTypeStr.equals(".txt")||fileTypeStr.equals(".doc")||fileTypeStr.equals(".docx")||fileTypeStr.equals(".pdf")||
										fileTypeStr.equals(".wps")||fileTypeStr.equals(".doc")||fileTypeStr.equals(".ppt")||fileTypeStr.equals(".pptx")){
						savePath= this.getServletContext().getRealPath("/files/documents");
						
						url=url+"/files/documents/"+pathName;
						map.put("homework_url", url);
						session.setAttribute("homeworkUrl",url);
						session.setAttribute("msg", "上传成功！");
					}else {
						session.setAttribute("msg", "注意的文件格式，视频只支持.mp4,.mov,.flv,.avi");
					}
					
					if(savePath!=null){
						File file=new File(savePath,pathName);
						fileItem.write(file);
						System.out.println("保存成功："+file.toString());
					}
				}
			
			}
			UserService userService=new UserService();
			//String username=(String) session.getAttribute("username");
			String username=request.getParameter("username");
			System.out.println("up:"+username);
			if(username!=null){
				User user=userService.loadByUserName(username);
				System.out.println(user.getId());
				String id=user.getId();
				map.put("user_id", id);
				session.setAttribute("userId", id);
				session.setAttribute("map", map);
				session.setAttribute("username", username);
				request.getRequestDispatcher("/CourseServlet?method=createCourse").forward(request, response);
			}else{
				session.setAttribute("msg", "创建课程失败");
				request.getRequestDispatcher("/teacher.jsp").forward(request, response);
			}
				
			System.out.println("UploadServlet任务结束---");
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
			
	}

}
