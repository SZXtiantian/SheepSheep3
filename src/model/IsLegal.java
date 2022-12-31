package model;

public class IsLegal implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean islegal;

	public boolean isIslegal() {
		return islegal;
	}

	public void setIslegal(boolean islegal) {
		this.islegal = islegal;
	}

	public IsLegal(boolean islegal) {
		super();
		this.islegal = islegal;
	}
	
}
