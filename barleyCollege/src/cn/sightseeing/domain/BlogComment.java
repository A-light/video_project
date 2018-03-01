package cn.sightseeing.domain;

public class BlogComment {
	//| id | blog_id | user_id | text_content | dianzan_count |time
	private String id;
	private String blog_id;
	private String user_id;
	private String text_content;
	private String dianzan_count;
	private String time;
	public BlogComment() {
		super();
	}
	
	public BlogComment(String id, String blog_id, String user_id, String text_content, String dianzan_count,
			String time) {
		super();
		this.id = id;
		this.blog_id = blog_id;
		this.user_id = user_id;
		this.text_content = text_content;
		this.dianzan_count = dianzan_count;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public String getDianzan_count() {
		return dianzan_count;
	}
	public void setDianzan_count(String dianzan_count) {
		this.dianzan_count = dianzan_count;
	}

	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "BlogComment [id=" + id + ", blog_id=" + blog_id + ", user_id=" + user_id + ", text_content="
				+ text_content + ", dianzan_count=" + dianzan_count + ", time=" + time + "]";
	}

}
