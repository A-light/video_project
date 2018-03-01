package cn.sightseeing.domain;

public class Degree {
	private byte degreeID = 1;
	private String degreeName = "";
	public byte getDegreeID() {
		return degreeID;
	}
	public void setDegreeID(byte degreeID) {
		this.degreeID = degreeID;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	@Override
	public String toString() {
		return "Degree [degreeID=" + degreeID + ", degreeName=" + degreeName
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + degreeID;
		result = prime * result
				+ ((degreeName == null) ? 0 : degreeName.hashCode());
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
		Degree other = (Degree) obj;
		if (degreeID != other.degreeID)
			return false;
		if (degreeName == null) {
			if (other.degreeName != null)
				return false;
		} else if (!degreeName.equals(other.degreeName))
			return false;
		return true;
	}
}
