package cn.sightseeing.domain;

public class Professional {
	private int professionalID = 0;// 专业表编号
	private String professionalType = "";// 专业类别
	private String professionalName = "";// 专业名称
	private String professionalCode = "";// 专业代码
	public int getProfessionalID() {
		return professionalID;
	}
	public void setProfessionalID(int professionalID) {
		this.professionalID = professionalID;
	}
	public String getProfessionalType() {
		return professionalType;
	}
	public void setProfessionalType(String professionalType) {
		this.professionalType = professionalType;
	}
	public String getProfessionalName() {
		return professionalName;
	}
	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}
	public String getProfessionalCode() {
		return professionalCode;
	}
	public void setProfessionalCode(String professionalCode) {
		this.professionalCode = professionalCode;
	}
	@Override
	public String toString() {
		return "Professional [professionalID=" + professionalID
				+ ", professionalType=" + professionalType
				+ ", professionalName=" + professionalName
				+ ", professionalCode=" + professionalCode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((professionalCode == null) ? 0 : professionalCode.hashCode());
		result = prime * result + professionalID;
		result = prime
				* result
				+ ((professionalName == null) ? 0 : professionalName.hashCode());
		result = prime
				* result
				+ ((professionalType == null) ? 0 : professionalType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professional other = (Professional) obj;
		if (professionalCode == null) {
			if (other.professionalCode != null)
				return false;
		} else if (!professionalCode.equals(other.professionalCode))
			return false;
		if (professionalID != other.professionalID)
			return false;
		if (professionalName == null) {
			if (other.professionalName != null)
				return false;
		} else if (!professionalName.equals(other.professionalName))
			return false;
		if (professionalType == null) {
			if (other.professionalType != null)
				return false;
		} else if (!professionalType.equals(other.professionalType))
			return false;
		return true;
	}
	
}
