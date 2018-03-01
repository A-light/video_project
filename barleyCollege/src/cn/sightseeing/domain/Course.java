package cn.sightseeing.domain;

public class Course {
	// id | video_url    
	//	| homework_url         
	//| user_id | created_time |
	//ending_time | course_content    
	// | course_name     | type_of | notice_content   
	// | video_id | status |
	private String id;
	private String video_url;
	private String homework_url;
	private int user_id;
	private String created_time;
	private String ending_time;
	private String course_content;
	private String course_name;
	private String type_of;
	private String notice_content;
	private String cuser_id;
	
	
	public String getCuser_id() {
		return cuser_id;
	}
	public void setCuser_id(String cuser_id) {
		this.cuser_id = cuser_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getHomework_url() {
		return homework_url;
	}
	public void setHomework_url(String homework_url) {
		this.homework_url = homework_url;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getEnding_time() {
		return ending_time;
	}
	public void setEnding_time(String ending_time) {
		this.ending_time = ending_time;
	}
	public String getCourse_content() {
		return course_content;
	}
	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getType_of() {
		return type_of;
	}
	public void setType_of(String type_of) {
		this.type_of = type_of;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	
	public Course(String id, String video_url, String homework_url, int user_id, String created_time,
			String ending_time, String course_content, String course_name, String type_of, String notice_content,
			String cuser_id) {
		super();
		this.id = id;
		this.video_url = video_url;
		this.homework_url = homework_url;
		this.user_id = user_id;
		this.created_time = created_time;
		this.ending_time = ending_time;
		this.course_content = course_content;
		this.course_name = course_name;
		this.type_of = type_of;
		this.notice_content = notice_content;
		this.cuser_id = cuser_id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", video_url=" + video_url + ", homework_url=" + homework_url + ", user_id="
				+ user_id + ", created_time=" + created_time + ", ending_time=" + ending_time + ", course_content="
				+ course_content + ", course_name=" + course_name + ", type_of=" + type_of + ", notice_content="
				+ notice_content + ", cuser_id=" + cuser_id + "]";
	}
	public Course() {
		super();
	}
	
	
	
	
}
