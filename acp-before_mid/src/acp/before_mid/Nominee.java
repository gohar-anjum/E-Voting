package acp.before_mid;

import java.io.Serializable;

public class Nominee implements Serializable {
	private int nomId;
	private String nomName;
	private String nomPosition;
	private int nomAge;
	
	

	public Nominee() {
		super();
		
	}

	public Nominee(int nomId, String nomName, String nomPosition, int nomAge) {
		super();
		this.nomId = nomId;
		this.nomName = nomName;
		this.nomPosition = nomPosition;
		this.nomAge = nomAge;
	}

	public String getNomName() {
		return nomName;
	}

	public void setNomName(String nomName) {
		this.nomName = nomName;
	}
	
	public String getNomPosition() {
		return nomPosition;
	}

	public void setNomPosition(String nomPosition) {
		this.nomPosition = nomPosition;
	}	

	public int getNomAge() {
		return nomAge;
	}

	public void setNomAge(int nomAge) {
		this.nomAge = nomAge;
	}
	
	public void setNomId(int nomId) {
		this.nomId = nomId;
	}
	public int getNomId() {
		return nomId;
	}

	@Override
	public String toString() {
		return "Nominee [ Id=" + nomId + ", Name=" + nomName + ", Position=" + nomPosition + "]";
	}

}
