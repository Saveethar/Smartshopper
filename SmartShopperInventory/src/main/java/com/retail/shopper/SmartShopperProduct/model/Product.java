package com.retail.shopper.SmartShopperProduct.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "name", unique = true, nullable = false)
	@NotNull(message = "Product name is required.")
	@Basic(optional = false)
	private String name;

	@Column(name = "description", nullable = true)
	@Basic(optional = true)
	public String description;

	@Column(name = "unitPrice", nullable = false)
	@NotNull(message = "Product price is required.")
	@Basic(optional = false)
	private Double unitPrice;

	@Column(name = "quantity", nullable = false)
	@NotNull(message = "Quantity is required.")
	@Basic(optional = false)
	private int quantity;

	
	@Column(name = "pictureUrl", nullable = true)
	@Basic(optional = true)
	private String pictureUrl;

	@Column(name = "status")
	public boolean status = true;

	@Column(name = "created_at", nullable = false)
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name = "fullPrice", unique = true, nullable = false)
	@NotNull(message = "Calculated price is required.")
	@Basic(optional = false)
	private Double fullPrice=0.0;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
