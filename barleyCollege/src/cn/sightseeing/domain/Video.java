package cn.sightseeing.domain;

public class Video {
//	 id | user_id | course_id | video_url |video_comment
	private String id;
	private String user_id;
	private String course_id;
	private String video_url;
	private String video_comment;
	
	public Video(String id, String user_id, String course_id, String video_url, String video_comment) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.video_url = video_url;
		this.video_comment = video_comment;
	}
	
	public Video() {
		super();
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
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getVideo_comment() {
		return video_comment;
	}
	public void setVideo_comment(String video_comment) {
		this.video_comment = video_comment;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", user_id=" + user_id + ", course_id=" + course_id + ", video_url=" + video_url
				+ ", video_comment=" + video_comment + "]";
	}
	
	
}
