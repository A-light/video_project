package cn.sightseeing.domain;

public class VideoComment {
	//id | user_id | course_id | comment_content  | comment_time        | author_name |
	private String id;
	private String user_id;
	private String course_id;
	private String comment_content;
	private String comment_time;
	private String author_name;
	private String dianzan;
	
	
	public VideoComment(String id, String user_id, String course_id, String comment_content, String comment_time,
			String author_name, String dianzan) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.comment_content = comment_content;
		this.comment_time = comment_time;
		this.author_name = author_name;
		this.dianzan = dianzan;
	}
	public VideoComment() {
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public String getDianzan() {
		return dianzan;
	}
	public void setDianzan(String dianzan) {
		this.dianzan = dianzan;
	}
	@Override
	public String toString() {
		return "VideoComment [id=" + id + ", user_id=" + user_id + ", course_id=" + course_id + ", comment_content="
				+ comment_content + ", comment_time=" + comment_time + ", author_name=" + author_name + ", dianzan="
				+ dianzan + "]";
	}
}
