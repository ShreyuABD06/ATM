	package com.spaneos.atm_simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ATM {
	public static void main(String[] args) {
		int choice = 0;
		DBInteractions dbc = new DBInteractions();
		Scanner sc = new Scanner(System.in);
		System.out.println("****************************************");
		System.out.println("               WELCOME					");
		System.out.println("                  TO                    ");
		System.out.println("             SYNDICATE ATM              ");
		System.out.println("****************************************");
		System.out.println();
		do {
			System.out.println("1.ADMIN\n2.USER");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:// Admin Case
				System.out.println("Enter your E-mail :");
				String username = sc.next();
				System.out.println("Enter your Password :");
				String password = sc.next();
				boolean check=dbc.validateAdmin(username,password);
				if (check) {
					System.out.println("Welcome Admin");
					int adminChoice = 0;
					while (adminChoice != 6) {
						System.out.println("1.Create Customer");
						System.out.println("2.Create Account Type");
						System.out.println("3.Create Account");
						System.out.println("4.Activate or Deactivate Account");
						System.out.println("5.Generate Report");
						System.out.println("Enter your choice :");
						adminChoice = sc.nextInt();
						switch (adminChoice) {
						case 1:
							System.out.println("Enter custId,firstName,secondName,email,address,city,state,pincode");
							Customer cs = new Customer(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(),
									sc.next(), sc.next(), sc.next());
							dbc.insertCustomerIntoDB(cs);
							dbc.releaseResources();
							break;
						case 2:
							System.out.println("Enter AcctypeId,typeName and rateOfInterest");
							AccType at = new AccType(sc.next(), sc.next(), sc.next());
							dbc.createAccType(at);
							dbc.releaseResources();
							break;
						case 3:
							System.out.println("Enter AccNo,customerId,AcctypeId,balance and AccStatus");
							Account acc = new Account(sc.nextInt(), sc.nextInt(), sc.next(), sc.nextFloat(), sc.next());
							dbc.createAccount(acc);
							dbc.releaseResources();
							break;
						case 4:
							System.out.println("Enter AccNo to deactivate the account");
							int accNo = sc.nextInt();
							System.out.println("Enter new Account Status");
							String newAccStatus = sc.next();
							int i = dbc.updateAccountStatusByAccNo(accNo, newAccStatus);
							if (i > 0) {
								System.out.println("updated successfully");
							} else {
								System.out.println("Not updated!!!");
							}
							dbc.releaseResources();
							break;
						case 5:
							ArrayList<Account> accList = dbc.generateReport();
							if (accList != null) {
								Iterator<Account> accItr = accList.iterator();
								while (accItr.hasNext()) {
									System.out.println(accItr.next());
								}
							} else {
								System.out.println("");
							}
							break;
						default:
							System.out.println("Enter valid Choice");

						}
					}
				} else {
					System.out.println("Invalid Credentials!!");
				}
				break;
			case 2:// User case
				System.out.println("Welcome User");
				System.out.println("Enter your pincode");
				String pincode = sc.next();
				if (pincode.equals("4527")) {
					System.out.println("1.Deposit Money");
					System.out.println("2.Withdraw Money");
					System.out.println("3.Balance Enquiry by Accno");
					System.out.println("4.Reports");
					System.out.println("Enter your choice");
					int userChoice = sc.nextInt();
					switch (userChoice) {
					case 1:
						System.out.println("Enter your accNo");
						int accNo = sc.nextInt();
						System.out.println("Enter how much money you want to deposit");
						float amount = sc.nextFloat();
						int i = dbc.depositeMoney(accNo, amount);
						if (i > 0) {
							System.out.println("Money added successfully");
						} else {
							System.out.println("Deposite Failed");
						}
						dbc.releaseResources();
						break;
					case 2:
						System.out.println("Enter accoNo");
						int accNo1 = sc.nextInt();
						System.out.println("Enter how much you want to withdraw");
						float amount1 = sc.nextFloat();
						int j = dbc.withdrawMoney(accNo1, amount1);
						if (j > 0) {
							System.out.println("Successful withdraw");
						} else {
							System.out.println("Insufficient balance");
						}
						break;
					case 3:
						System.out.println("Enter your account Number :");
						int accNo2 = sc.nextInt();
						Account acc = dbc.balanceEnquiry(accNo2);
						if (acc != null) {
							System.out.println("Details of Account with accno " + accNo2 + " are " + acc);
						} else {
							System.out.println("Unknown accno");
						}
						dbc.releaseResources();
						break;
					case 4:
						ArrayList<Transaction> tsList = dbc.generateTransaction();
						if (tsList != null) {
							Iterator<Transaction> tsItr = tsList.iterator();
							while (tsItr.hasNext()) {
								System.out.println(tsItr.next());
							}
						} else {
							System.out.println("No data available");
						}
						break;
					default:
						System.out.println("Enter valid choice");
						break;
					}
				} else {
					System.out.println("Enter valid choic!!!!!");
				}
				break;
			default:
				System.out.println("Enter correct pincode!");
				break;
			}

		} while (choice != 3);
		sc.close();

	}
}
