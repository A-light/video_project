package cn.sightseeing.domain;

/**
 * 2017年4月9日
 * @author gw
 *
 */
public class User {
	/*
	 * 对应数据库字段
	 * id | username | password | name   | age  | 
	 * gender | idcard | city | school   | shenfen | xueli    | pic  |
	 */
	private String id;//主键
	private String username;//
	private String password;
	private String email;
	private String name;
	private int age;
	private String gender;
	private String idcard;
	private String city;
	private String school;
	private String shenfen;
	private String xueli;
	private String pic;
	
	public User(String id, String username, String shenfen,String password, String email,String name, int age, String gender, String idcard,
			String city, String school, String xueli, String pic) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email=email;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.idcard = idcard;
		this.city = city;
		this.school = school;
		this.shenfen = shenfen;
		this.xueli = xueli;
		this.pic = pic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getShenfen() {
		return shenfen;
	}
	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
				+ name + ", age=" + age + ", gender=" + gender + ", idcard=" + idcard + ", city=" + city + ", school="
				+ school + ", shenfen=" + shenfen + ", xueli=" + xueli + ", pic=" + pic + "]";
	}
	public User() {
		super();
	}
	
	public int getUserType() {
		return 0;
	}
}
