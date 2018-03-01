package cn.sightseeing.domain;

public class Collection {
	private String id;
	private String user_id;
	private String course_name;
	private String status;
	
	public Collection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Collection(String id, String user_id, String course_name, String status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.course_name = course_name;
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Collection [id=" + id + ", user_id=" + user_id + ", course_name=" + course_name + ", status=" + status
				+ "]";
	}
	
}
