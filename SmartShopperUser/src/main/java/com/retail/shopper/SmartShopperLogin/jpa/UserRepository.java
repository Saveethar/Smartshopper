package com.retail.shopper.SmartShopperLogin.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.shopper.SmartShopperLogin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 List<User> findByUserNameAndEmail(String userName, String email);
}
