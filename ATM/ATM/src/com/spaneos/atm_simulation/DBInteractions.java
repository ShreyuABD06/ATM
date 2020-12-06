package com.spaneos.atm_simulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBInteractions implements ATM_Interface {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	private Connection connect() {
		String url = "jdbc:mysql://localhost:3306/spaneos_atm";
		try {
			conn = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public void insertCustomerIntoDB(Customer cs) {
		conn = connect();
		String insert = "insert into customer values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, cs.getCustomerId());
			pstmt.setString(2, cs.getFirstName());
			pstmt.setString(3, cs.getSecondName());
			pstmt.setString(4, cs.getEmail());
			pstmt.setString(5, cs.getAddress());
			pstmt.setString(6, cs.getCity());
			pstmt.setString(7, cs.getState());
			pstmt.setString(8, cs.getPincode());
			pstmt.executeUpdate();
			System.out.println("DataInserted Successfully");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}

	}

	public void releaseResources() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}

	}

	public void createAccType(AccType at) {
		conn = connect();
		String sql = "insert into acc_type values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getAcctypeId());
			pstmt.setString(2, at.getTypeName());
			pstmt.setString(3, at.getRateOfInterest());
			pstmt.executeUpdate();
			System.out.println("Data Inserted Successfully");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
	}

	public void createAccount(Account acc) {
		conn = connect();
		String sql = "insert into account values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acc.getAccNo());
			pstmt.setInt(2, acc.getCustomerId());
			pstmt.setString(3, acc.getAccTypeId());
			pstmt.setFloat(4, acc.getBalance());
			pstmt.setString(5, acc.getAccStatus());
			pstmt.executeUpdate();
			System.out.println("Data Inserted Successfully");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}

	}

	public int updateAccountStatusByAccNo(int accNo, String newAccStatus) {
		conn = connect();
		int i = 0;
		String sql = "update account set acc_status=? where accno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newAccStatus);
			pstmt.setInt(2, accNo);
			i = pstmt.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return i;
	}

	public ArrayList<Account> generateReport() {
		conn = connect();
		Account acc = null;
		ArrayList<Account> accList = new ArrayList<>();
		String sql = "select * from account";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				acc = new Account();
				acc.setAccNo(rs.getInt(1));
				acc.setCustomerId(rs.getInt(2));
				acc.setAccTypeId(rs.getString(3));
				acc.setBalance(rs.getFloat(4));
				acc.setAccStatus(rs.getString(5));
				accList.add(acc);
			}
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return accList;
	}

	public int depositeMoney(int accNo, float amount) {
		conn = connect();
		int i = 0;
		String sql = "update account set balance=balance+? where accno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, amount);
			pstmt.setInt(2, accNo);
			i = pstmt.executeUpdate();
			transaction(accNo, amount, "deposit");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return i;
	}

	public void transaction(int accNo, float amount, String txStatus) {
		conn = connect();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Todays date(yyyymmdd) :");
		String date = sc.next();
		String sql = "insert into transaction(accno,amount,tx_status,tx_date) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accNo);
			pstmt.setFloat(2, amount);
			pstmt.setString(3, txStatus);
			pstmt.setString(4, date);
			pstmt.executeUpdate();
			System.out.println("Transaction made!!");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		sc.close();
	}

	public int withdrawMoney(int accNo1, float amount1) {
		conn = connect();
		int i = 0;
		String sql = "update account set balance=balance-? where accno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, amount1);
			pstmt.setInt(2, accNo1);
			i = pstmt.executeUpdate();
			transaction(accNo1, amount1, "withdraw");
			System.out.println("Withdraw Successfull");
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return i;
	}

	public Account balanceEnquiry(int accNo2) {
		conn = connect();
		String sql = "select * from account where accno=?";
		Account acc = new Account();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accNo2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				acc = new Account();
				acc.setAccNo(rs.getInt(1));
				acc.setCustomerId(rs.getInt(2));
				acc.setAccTypeId(rs.getString(3));
				acc.setBalance(rs.getFloat(2));
				acc.setAccStatus(rs.getString(5));
			}
		} catch (SQLException s) {
			System.out.println(s.getErrorCode());
		}
		return acc;
	}

	public ArrayList<Transaction> generateTransaction() {
		conn = connect();
		Transaction ts = null;
		String sql = "select * from transaction order by tx_date limit 5";
		ArrayList<Transaction> tsList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ts = new Transaction();
				ts.setTxId(rs.getInt(1));
				ts.setAccNo(rs.getInt(2));
				ts.setAmount(rs.getFloat(3));
				ts.setTxStatus(rs.getString(4));
				ts.setTxDate(rs.getString(5));
				tsList.add(ts);
			}
		} catch (SQLException s1) {
			System.out.println(s1.getMessage());
		}

		return tsList;
	}

	public boolean validateAdmin(String username, String password) {
		conn = connect();
		String sql = "select * from admin where email=? and password=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return false;
	}

}
