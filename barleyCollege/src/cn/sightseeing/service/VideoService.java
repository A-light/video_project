package cn.sightseeing.service;

import java.rmi.ServerException;
import java.util.List;

import cn.sightseeing.dao.CourseDao;
import cn.sightseeing.dao.VideoDao;
import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.Progress;
import cn.sightseeing.domain.Video;
import cn.sightseeing.domain.VideoComment;

/**
 * @author gw
 *该类负责获取评论、提交评论和获取课程的id
 */
public class VideoService {
	private CourseDao courseDao=new CourseDao();
	private VideoDao  videoDao=new VideoDao();
	
	public List<VideoComment> loadComment(String course_id) throws ServerException{
		return courseDao.loadComment(course_id);
	}
	public boolean submitComment(String user_id,String course_id,String video_comment,
			String author_name) throws ServerException{
		return courseDao.submitComment(user_id, course_id, video_comment,author_name);
	}
	public String getCourseId(String user_id,String course_name) throws ServerException {
		return courseDao.getCourseId(user_id, course_name);
	}
	public String getCourseId(String course_name) throws ServerException {
		return courseDao.getCourseId(course_name);
	}
	public Double loadaytime(String user_id,String course_id) throws ServerException{
		Progress progress=videoDao.loadProgressById(user_id, course_id);
		return progress.getPlaytime();
	}
	
	
	public boolean setProgress(double playtime,String user_id,String course_id) throws ServerException{
		return videoDao.setProgress(playtime, user_id, course_id);
	}
	public boolean updateProgress(double playtime,String user_id,String course_id) throws ServerException{
		return videoDao.updateProgress(playtime, user_id, course_id);
	}
	public int queryProgress(String user_id,String course_id) throws ServerException{
		return videoDao.queryProgress(user_id, course_id);
	}
	public List<Progress> loadProgressById(String course_id) throws ServerException{
		return videoDao.loadProgressById(course_id);
	}
	
	public boolean accomplish(String user_id,String course_id) throws ServerException{
		return videoDao.setProgressStatus("1", user_id, course_id);
	}
}
