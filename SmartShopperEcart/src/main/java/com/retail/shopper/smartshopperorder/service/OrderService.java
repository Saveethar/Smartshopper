package com.retail.shopper.smartshopperorder.service;

import org.springframework.stereotype.Component;

import com.retail.shopper.smartshopperorder.model.OrderAPIResponse;


@Component
public interface OrderService {

	public OrderAPIResponse checkoutOrder(Long customerId, Long productId);

}