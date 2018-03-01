package cn.sightseeing.domain;

import java.sql.Timestamp;

public class UserLoginInfo {
	private long uLIID = 0;// 用户登录信息记录ID
	private Timestamp loginTime = null;// 登录时间
	private String loginIP = "";// 登录IP
	private String loginAdress = "";// 登录地址
	private String loginBrower = "";// 登录浏览器
	private String loginWay = "";// 登录方式
	private String loginDevice = "";// 登录设备
	private String loginUsername = "";// 登录用户名

	public long getuLIID() {
		return uLIID;
	}

	public void setuLIID(long uLIID) {
		this.uLIID = uLIID;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginAdress() {
		return loginAdress;
	}

	public void setLoginAdress(String loginAdress) {
		this.loginAdress = loginAdress;
	}

	public String getLoginBrower() {
		return loginBrower;
	}

	public void setLoginBrower(String loginBrower) {
		this.loginBrower = loginBrower;
	}

	public String getLoginWay() {
		return loginWay;
	}

	public void setLoginWay(String loginWay) {
		this.loginWay = loginWay;
	}

	public String getLoginDevice() {
		return loginDevice;
	}

	public void setLoginDevice(String loginDevice) {
		this.loginDevice = loginDevice;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	@Override
	public String toString() {
		return "UserLoginInfo [uLIID=" + uLIID + ", loginTime=" + loginTime + ", loginIP=" + loginIP + ", loginAdress="
				+ loginAdress + ", loginBrower=" + loginBrower + ", loginWay=" + loginWay + ", loginDevice="
				+ loginDevice + ", loginUsername=" + loginUsername + "]";
	}

}
