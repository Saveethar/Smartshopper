package com.retail.shopper.SmartShopperPayment.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(name = "orderId", nullable = false)
	@NotNull(message = "Order Id is required.")
	@Basic(optional = false)
	private Long orderId;

	@Column(name = "paymentStatus")
	public boolean paymentStatus = false;

	@Column(name = "paymentDate", nullable = true)
	private Date paymentDate;
	
	@Column(name = "ccNumber", nullable = false)
	@NotNull(message = "ccNumber is required.")
	@Basic(optional = false)
	private Long ccNumber;
	
	@Column(name = "ccType", nullable = false)
	@NotNull(message = "ccType is required.")
	@Basic(optional = false)
	private String ccType;

	@Column(name = "ccExpiryDate", nullable = true)
	private Date ccExpiryDate;

	/**
	 * @return the paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the paymentStatus
	 */
	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the ccNumber
	 */
	public Long getCcNumber() {
		return ccNumber;
	}

	/**
	 * @param ccNumber the ccNumber to set
	 */
	public void setCcNumber(Long ccNumber) {
		this.ccNumber = ccNumber;
	}

	/**
	 * @return the ccType
	 */
	public String getCcType() {
		return ccType;
	}

	/**
	 * @param ccType the ccType to set
	 */
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	/**
	 * @return the ccExpiryDate
	 */
	public Date getCcExpiryDate() {
		return ccExpiryDate;
	}

	/**
	 * @param ccExpiryDate the ccExpiryDate to set
	 */
	public void setCcExpiryDate(Date ccExpiryDate) {
		this.ccExpiryDate = ccExpiryDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderId=" + orderId
				+ ", paymentStatus=" + paymentStatus + ", paymentDate="
				+ paymentDate + ", ccNumber=" + ccNumber + ", ccType=" + ccType
				+ ", ccExpiryDate=" + ccExpiryDate + "]";
	}
	
	
}
