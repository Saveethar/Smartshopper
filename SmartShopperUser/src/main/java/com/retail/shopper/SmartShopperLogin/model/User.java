package com.retail.shopper.SmartShopperLogin.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "first_name", nullable = true)
	@Length(min = 3, max = 50)
	@NotEmpty(message = "Please provide your first name")
	private String firstName;

	@Column(name = "last_name", nullable = true)
	@Length(min = 1, max = 50)
	@NotEmpty(message = "Please provide your last name")
	private String lastName;

	@Column(name = "phone", nullable = true)
	@NotEmpty(message = "phone is mandatory. Please send valid phone number")
	@Length(min = 8, max = 15)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phone;

	@Column(name = "created_at", nullable = false)
	@CreatedDate
	private Date createdAt;

	@Column(name = "Address", nullable = true)
	public String address;

	@Column(name = "login_Success")
	public boolean loginSuccess = false;
	
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
	
	public User() {
	}

	public User(String userName, String email) {
		this.userName = userName;
		this.password = password;
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
	
    /*@EmbeddedId
    private LoginId uniqueKey;

	public LoginId getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(LoginId uniqueKey) {
		this.uniqueKey = uniqueKey;
	}*/

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id
	 *            the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

}