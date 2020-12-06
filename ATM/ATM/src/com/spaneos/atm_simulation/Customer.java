package com.spaneos.atm_simulation;

public class Customer {
	public Customer() {
		super();
	}

	public Customer(int customerID, String firstName, String secondName, String email, String address, String city,
			String state, String pincode) {
		super();
		this.customerId = customerID;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	private int customerId;

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", email=" + email + ", address=" + address + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}

	private String firstName;
	private String secondName;
	private String email;
	private String address;
	private String city;
	private String state;
	private String pincode;

}
