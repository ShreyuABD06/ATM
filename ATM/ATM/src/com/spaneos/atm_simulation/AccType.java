package com.spaneos.atm_simulation;

public class AccType {
	public AccType() {
		super();
	}

	public AccType(String acctypeId, String typeName, String rateOfInterest) {
		super();
		this.acctypeId=acctypeId;
		this.typeName=typeName;
		this.rateOfInterest=rateOfInterest;
	}

	private String acctypeId;

	public String getAcctypeId() {
		return acctypeId;
	}

	public void setAcctypeId(String acctypeId) {
		this.acctypeId = acctypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	private String typeName;
	private String rateOfInterest;

	@Override
	public String toString() {
		return "AccType [acctypeId=" + acctypeId + ", typeName=" + typeName + ", rateOfInterest=" + rateOfInterest
				+ "]";
	}
}
