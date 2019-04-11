package com.retail.shopper.smartshopperorder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.retail.shopper.smartshopperorder.service.model.Product;

@Entity
@Table(name="\"Orders\"")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(name = "customerId", nullable = false)
	@NotNull(message = "Customer Id is required.")
	@Basic(optional = false)
	private Long customerId;

	// Should handle multiple product IDs
	@Column(name = "productId", nullable = false)
	@NotNull(message = "Product Id is required.")
	@Basic(optional = false)
	private Long productId;
	
	@Embedded
	@ElementCollection
	private Collection<Product> productList = new ArrayList<Product>();

	/**
	 * @return the productList
	 */
	public Collection<Product> getProductList() {
		return productList;
	}

	@Column(name = "fulfilmentStatus")
	public boolean fulfilmentStatus = false;

	@Column(name = "fulfilmentDate", nullable = true)
	private Date fulfilmentDate;
	
	@Column(name = "created_at", nullable = false)
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isFulfilmentStatus() {
		return fulfilmentStatus;
	}

	public void setFulfilmentStatus(boolean fulfilmentStatus) {
		this.fulfilmentStatus = fulfilmentStatus;
	}

	public Date getFulfilmentDate() {
		return fulfilmentDate;
	}

	public void setFulfilmentDate(Date fulfilmentDate) {
		this.fulfilmentDate = fulfilmentDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
