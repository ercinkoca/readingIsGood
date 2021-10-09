package com.getir.books.model;

import java.io.Serializable;

public class CustomerRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2550962759960059337L;
	
	public CustomerRequest() {
		super();
	}
	
	private String customerName;
	private String customerSurname;
	private String email;
	private String address;

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
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
	
	
	

}
