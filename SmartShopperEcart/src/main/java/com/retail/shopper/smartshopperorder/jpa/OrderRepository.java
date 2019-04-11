package com.retail.shopper.smartshopperorder.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.shopper.smartshopperorder.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
