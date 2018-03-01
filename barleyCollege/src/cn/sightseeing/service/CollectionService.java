package cn.sightseeing.service;

import java.rmi.ServerException;
import java.util.List;

import cn.sightseeing.dao.VideoDao;
import cn.sightseeing.domain.Collection;
import cn.sightseeing.domain.Course;

public class CollectionService {
	private VideoDao videoDao=new VideoDao(); 
	
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public boolean collectVideo(String user_id,String course_name) throws ServerException {
		int num=videoDao.countByIdAndName(user_id, course_name);
		if(num<=0){
			return videoDao.collectVideo(user_id, course_name);
		}else{
			return false;
		}
	}

	/**
	 * @param user_id
	 * @return
	 * @throws ServerException
	 */
	public List<Course> loadCollection(String user_id) throws ServerException{
		return videoDao.loadCollection(user_id);
	}
	/**
	 * @param user_id
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public String queryStatus(String user_id,String course_name) throws ServerException {
		Collection collection=videoDao.loadCollection(user_id, course_name);
		return collection.getStatus();
	}
	/**
	 * @param course_name
	 * @return
	 * @throws ServerException
	 */
	public int countByCourseName(String course_name) throws ServerException{
		return videoDao.countByCourseName(course_name);
	}
	
	public boolean setStatus(String status,String user_id,String course_name) throws ServerException{
		return videoDao.setStatus(status,user_id, course_name);
	}

	public int countByIdAndName(String user_id, String course_name) throws ServerException {
		return videoDao.countByIdAndName(user_id, course_name);
	}
}

