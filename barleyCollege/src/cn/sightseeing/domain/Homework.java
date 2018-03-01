package cn.sightseeing.domain;

public class Homework {
//	| id | download_url | created_time | 
//	ending_time | upload_url | user_id | course_id
	private String downloadUrl;
	private String uploadUrl;
	private String userId;
	private String courseId;
	public Homework(String downloadUrl, String uploadUrl, String userId, String courseId) {
		super();
		this.downloadUrl = downloadUrl;
		this.uploadUrl = uploadUrl;
		this.userId = userId;
		this.courseId = courseId;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUploadUrl() {
		return uploadUrl;
	}
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Override
	public String toString() {
		return "Homework [downloadUrl=" + downloadUrl + ", uploadUrl=" + uploadUrl + ", userId=" + userId
				+ ", courseId=" + courseId + "]";
	}
	
}
