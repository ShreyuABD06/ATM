package com.spaneos.atm_simulation;

public class Account {
	public Account() {
		super();
	}

	public Account(int accNo, int customerId, String accTypeId, float balance, String accStatus) {
		super();
		this.accNo = accNo;
		this.customerId = customerId;
		this.accTypeId = accTypeId;
		this.balance = balance;
		this.accStatus = accStatus;
	}

	private int accNo;

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(String accTypeId) {
		this.accTypeId = accTypeId;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	private int customerId;
	private String accTypeId;
	private float balance;
	private String accStatus;

	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", customerId=" + customerId + ", accTypeId=" + accTypeId + ", balance="
				+ balance + ", accStatus=" + accStatus + "]";
	}
}
