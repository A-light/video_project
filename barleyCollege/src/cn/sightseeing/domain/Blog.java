package cn.sightseeing.domain;

public class Blog {
	//id | text_content | created_time | user_id | update_time 
	//| dianzan_count | title | visit_times |
	private String id;
	private String text_content;
	private String created_time;
	private String user_id;
	private String update_time;
	private String dianzan_count;
	private String title;
	private String visit_time;
	
	public Blog() {
		super();
	}
	public Blog(String id, String text_content, String created_time, String user_id, String update_time,
			String dianzan_count, String title, String visit_time) {
		super();
		this.id = id;
		this.text_content = text_content;
		this.created_time = created_time;
		this.user_id = user_id;
		this.update_time = update_time;
		this.dianzan_count = dianzan_count;
		this.title = title;
		this.visit_time = visit_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getDianzan_count() {
		return dianzan_count;
	}
	public void setDianzan_count(String dianzan_count) {
		this.dianzan_count = dianzan_count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", text_content=" + text_content + ", created_time=" + created_time + ", user_id="
				+ user_id + ", update_time=" + update_time + ", dianzan_count=" + dianzan_count + ", title=" + title
				+ ", visit_time=" + visit_time + "]";
	}
}
