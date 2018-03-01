package cn.sightseeing.service;

import java.rmi.ServerException;
import java.util.List;

import cn.sightseeing.dao.VideoDao;
import cn.sightseeing.domain.Collection;
import cn.sightseeing.domain.Course;

public class StudentService {
	private VideoDao videoDao=new VideoDao();
	
	public List<Course> loadCollection(String user_id) throws ServerException{
		return videoDao.loadCollection(user_id);
	}
}
