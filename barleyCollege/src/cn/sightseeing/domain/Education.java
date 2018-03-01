package cn.sightseeing.domain;

public class Education {
	private byte educationID = 0;// 学历表编号
	private String educationName = "";// 学历名称

	public byte getEducationID() {
		return educationID;
	}

	public void setEducationID(byte educationID) {
		this.educationID = educationID;
	}

	public String getName() {
		return educationName;
	}

	public void setName(String name) {
		this.educationName = name;
	}

	@Override
	public String toString() {
		return "Education [educationID=" + educationID + ", educationName="
				+ educationName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + educationID;
		result = prime * result
				+ ((educationName == null) ? 0 : educationName.hashCode());
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
		Education other = (Education) obj;
		if (educationID != other.educationID)
			return false;
		if (educationName == null) {
			if (other.educationName != null)
				return false;
		} else if (!educationName.equals(other.educationName))
			return false;
		return true;
	}
}
