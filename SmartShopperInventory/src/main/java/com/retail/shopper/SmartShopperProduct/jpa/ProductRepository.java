package com.retail.shopper.SmartShopperProduct.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.shopper.SmartShopperProduct.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
