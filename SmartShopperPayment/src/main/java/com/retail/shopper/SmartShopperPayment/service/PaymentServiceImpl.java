package com.retail.shopper.SmartShopperPayment.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.shopper.SmartShopperPayment.jpa.PaymentRepository;
import com.retail.shopper.SmartShopperPayment.model.PaymentAPIResponse;
import com.retail.shopper.SmartShopperPayment.service.model.Order;
import com.google.gson.Gson;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles
			.lookup().lookupClass());

	@Autowired
	PaymentAPIResponse paymentAPIResponse;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private com.retail.shopper.SmartShopperPayment.util.Utils utils;

	/**
	 * It is shown that products are fetched from DB. Also commented block is
	 * shown where token is obtained and supplier APIs are invoked to populate
	 * product list
	 */
	@Override
	public PaymentAPIResponse completePayment(Long orderId) {
		try {
			String orderStr = utils.callApi(null, "GET",
					"http://localhost:9000/api/v1/order/"+orderId, null,
					null);
			Gson gson = new Gson();
			Order ordObj = new Order();
			ordObj = gson.fromJson(orderStr, ordObj.getClass());
			
			this.logger.info("Order Details retrieved= " + orderStr.toString());
			
			/**
			 * Here i need to call visa payment APIs and complete payment.
			 * Unfortunately not working
			 */
			
			//Process a Payment. Authorize the payment for the transaction.
			//https://sandbox.api.visa.com/cybersource/v2/payments?apikey={apikey}
			
			//Capture a Payment. Include the payment ID in the POST request to capture the payment amount.
			//https://sandbox.api.visa.com/cybersource/v2/payments/{id}/captures?apikey={apikey}
			
			//Process a Credit.POST to the credit resource to credit funds to a specified credit card.
			// Pay to merchant
			//https://sandbox.api.visa.com/cybersource/v2/credits?apikey={apikey}
				
		/*	String token = utils.callApi(null, "GET",
					https://sandbox.api.visa.com/cybersource/v2/payments?apikey="+apikey,null,
					null);			
	
		 */

			paymentAPIResponse.setErrorString(null);
			paymentAPIResponse.setStatus("Success");
			paymentAPIResponse.setSuccessString("Payment processed. Shopping completed");

		} catch (Exception e) {
			paymentAPIResponse.setStatus("Error");
			paymentAPIResponse
					.setErrorString("Error occured during payment");
			paymentAPIResponse.setSuccessString(null);
			logger.info("Error occured during payment");
		}
		return paymentAPIResponse;
	}

}
