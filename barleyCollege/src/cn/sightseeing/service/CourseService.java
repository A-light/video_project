package cn.sightseeing.service;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import cn.sightseeing.dao.CourseDao;
import cn.sightseeing.domain.Course;
import cn.sightseeing.domain.User;

public class CourseService {
	private CourseDao courseDao=new CourseDao();
	public boolean createCourse(Course course) throws SQLException, ServerException {
		int cnt=courseDao.countByCourseName(course.getCourse_name());
		System.out.println(cnt);
		if(courseDao.countByCourseName(course.getCourse_name())>0){
			return false;
		}else{
			System.out.println(course.getHomework_url());
			System.out.println(course.getCourse_name());
			courseDao.addCourse(course);
			return true;
		}
	}
	
	public void closeCourse(String courseName) throws SQLException {
		courseDao.uppdateCourseStatus(courseName);
	}
	
	public List<Course> loadCourse(String userId) throws ServerException{
		return courseDao.loadByUserId(userId);
	}
	public List<Course> loadCourse() throws ServerException{
		return courseDao.loadCourse();
	}
	public Course loadCourseByCourseId(String course_id) throws ServerException{
		return courseDao.loadCourseByCourseId(course_id);
	}
	public List<Course> loadCourseByType(String type_of) throws ServerException{
		return courseDao.loadByCourseType(type_of);
	}
}
