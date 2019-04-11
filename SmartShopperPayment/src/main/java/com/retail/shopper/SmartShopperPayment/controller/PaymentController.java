package com.retail.shopper.SmartShopperPayment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.shopper.SmartShopperPayment.exception.ResourceNotFoundException;
import com.retail.shopper.SmartShopperPayment.jpa.PaymentRepository;
import com.retail.shopper.SmartShopperPayment.model.Payment;
import com.retail.shopper.SmartShopperPayment.model.PaymentAPIResponse;
import com.retail.shopper.SmartShopperPayment.service.PaymentService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles
			.lookup().lookupClass());

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private com.retail.shopper.SmartShopperPayment.util.Utils utils;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	PaymentAPIResponse paymentAPIResponse;

	/**
	 * Get all product list from inventory.
	 *
	 * @return the list
	 */
	@GetMapping("/payments")
	public List<Payment> getAllPayment() {
		return paymentRepository.findAll();
	}

	/**
	 * Gets Product by id. Will be used by both internal and external users
	 */
	@GetMapping("/payment/{id}")
	public ResponseEntity<Payment> getOrderById(
			@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(orderId).orElseThrow(
				() -> new ResourceNotFoundException(
						"Payment not found with id :: " + orderId));
		return ResponseEntity.ok().body(payment);
	}

	// Add products - Admin usages
	@PostMapping("/payment/add")
	public String createPayment(@Valid @RequestBody Payment payment) {
		try {
			paymentAPIResponse = paymentService.completePayment(payment.getOrderId());
			
			if (paymentAPIResponse.getStatus().equals("Success")) {
				payment.setPaymentStatus(true);
				payment.setPaymentDate(new Date());
				paymentRepository.save(payment);
				
				utils.sendEmail("Smartshopper Ecart", "Smartshopper Shopping Completed");

				return "Smartshopper Payment Succees. End of Shopping flow.";
			} else {
				payment.setPaymentStatus(false);
				paymentRepository.save(payment);
				
				utils.sendEmail("Smartshopper Ecart", "Smartshopper Payment Failure");

				return "Smartshopper Payment Failure";
			}
		} catch (Exception e) {
			utils.getPrintableStackTrace(e);
			logger.info("Payment Failures: "
					+ utils.getPrintableStackTrace(e));
			return "Unable to Pay";
		}

		
	}

	// Can add REST methods to modify and delete product from product catalogue
	// for admin purposes
}
