package com.retail.shopper.smartshopperorder.service.model;

public class User {
	private long id;

	private String firstName;

	private String lastName;

	private String phone;

	public String address;

	private String userName;

	private String email;
	
	

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phone=" + phone + ", address=" + address
				+ ", userName=" + userName + ", email=" + email + "]";
	}
	

}