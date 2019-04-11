package com.retail.shopper.SmartShopperLogin.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class LoginId implements Serializable {

	
	@Column(name = "user_name", unique = true, nullable = false)
	@NotEmpty(message = "userName is mandatory")
	@Length(min = 3, max = 50)
	private String userName;

	@Column(name = "password", nullable = false)
	@NotEmpty(message = "password is mandatory")
	@Length(min = 3, max = 50)
	private String password;

	@Column(name = "email_address", nullable = false, unique = true)
	@NotEmpty(message = "email is mandatory. Please send valid email address")
	@Email(message = "Please provide a valid e-mail")
	private String email;

	public LoginId() {
	}

	public LoginId(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName
	 *            the first name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email
	 *            the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LoginId))
			return false;
		LoginId that = (LoginId) o;
		return Objects.equals(getUserName(), that.getUserName())
				&& Objects.equals(getPassword(), that.getPassword())
				&& Objects.equals(getEmail(), that.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserName(), getPassword(), getEmail());
	}

}
