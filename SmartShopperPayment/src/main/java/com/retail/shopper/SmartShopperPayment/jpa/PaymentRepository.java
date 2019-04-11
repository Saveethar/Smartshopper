package com.retail.shopper.SmartShopperPayment.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.retail.shopper.SmartShopperPayment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
