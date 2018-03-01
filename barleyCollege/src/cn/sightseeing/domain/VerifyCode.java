package cn.sightseeing.domain;

public class VerifyCode {
	private String phone = "";// 手机
	private String code = "";// 验证码

	public VerifyCode() {
	}

	public VerifyCode(String phone, String code) {
		this.phone = phone;
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
