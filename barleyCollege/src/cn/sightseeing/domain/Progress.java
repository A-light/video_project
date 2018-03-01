package cn.sightseeing.domain;

/**
 * 这是一个视频进度模块
 * @author gw
 *
 */
public class Progress {
	// id | user_id | course_id | playtime | percent 
	private String id;
	private String user_id;
	private String course_id;
	private double playtime;
	private double percent;
	
	
	public Progress(String id, String user_id, String course_id, double playtime, double percent) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.playtime = playtime;
		this.percent = percent;
	}
	public Progress() {
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

	public double getPlaytime() {
		return playtime;
	}
	public void setPlaytime(double playtime) {
		this.playtime = playtime;
	}
	public double getPercent() {
		return percent;
	}
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "Progress [id=" + id + ", user_id=" + user_id + ", course_id=" + course_id + ", playtime=" + playtime
				+ ", percent=" + percent + "]";
	}
	
}
