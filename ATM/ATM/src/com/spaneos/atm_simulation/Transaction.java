package com.spaneos.atm_simulation;

public class Transaction {
	public Transaction() {
		super();
	}

	public Transaction(int txId, int accNo, float amount, String txStatus, String txDate) {
		super();
		this.txId = txId;
		this.accNo = accNo;
		this.amount = amount;
		this.txStatus = txStatus;
		this.txDate = txDate;
	}

	private int txId;

	public int getTxId() {
		return txId;
	}

	public void setTxId(int txId) {
		this.txId = txId;
	}

	public int getAccNo() {
		return accNo;
	}

	@Override
	public String toString() {
		return "Transaction [txId=" + txId + ", accNo=" + accNo + ", amount=" + amount + ", txStatus=" + txStatus
				+ ", txDate=" + txDate + "]";
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}

	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	private int accNo;
	private float amount;
	private String txStatus;
	private String txDate;
}
