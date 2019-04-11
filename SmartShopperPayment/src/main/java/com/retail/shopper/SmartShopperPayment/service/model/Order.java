package com.retail.shopper.SmartShopperPayment.service.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class Order {

	private Long orderId;

	public boolean fulfilmentStatus = false;

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @return the fulfilmentStatus
	 */
	public boolean isFulfilmentStatus() {
		return fulfilmentStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [orderId=" + orderId 
				+ ", fulfilmentStatus=" + fulfilmentStatus + "]";
	}

	
}
