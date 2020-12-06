package com.spaneos.atm_simulation;

import java.util.ArrayList;

public interface ATM_Interface {
	public void insertCustomerIntoDB(Customer cs);

	public void createAccType(AccType at);

	public void createAccount(Account acc);

	public int updateAccountStatusByAccNo(int accNo, String newAccStatus);

	public ArrayList<Account> generateReport();

	public int depositeMoney(int accNo, float amount);

	public void transaction(int accNo, float amount, String txStatus);

	public int withdrawMoney(int accNo1, float amount1);

	public Account balanceEnquiry(int accNo2);

	public ArrayList<Transaction> generateTransaction();

	public boolean validateAdmin(String username, String password);

	public void releaseResources();
}
