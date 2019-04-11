package com.retail.shopper.SmartShopperPayment.service;

import org.springframework.stereotype.Component;

import com.retail.shopper.SmartShopperPayment.model.PaymentAPIResponse;


@Component
public interface PaymentService {

	public PaymentAPIResponse completePayment(Long orderId);

}