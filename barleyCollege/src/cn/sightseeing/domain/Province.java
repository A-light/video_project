package cn.sightseeing.domain;

import java.util.List;

public class Province {
	private int pid;
	private String name;
	private List<City> city;
	public Province(int pid, String name, List<City> city) {
		super();
		this.pid = pid;
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<City> getCity() {
		return city;
	}
	public void setCity(List<City> city) {
		this.city = city;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Province [pid=" + pid + ", name=" + name + ", city=" + city + ", getName()=" + getName()
				+ ", getCity()=" + getCity() + ", getPid()=" + getPid() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
