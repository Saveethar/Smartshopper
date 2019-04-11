package com.retail.shopper.SmartShopperLogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.shopper.SmartShopperLogin.exception.ResourceNotFoundException;
import com.retail.shopper.SmartShopperLogin.jpa.UserRepository;
import com.retail.shopper.SmartShopperLogin.model.User;
import com.retail.shopper.SmartShopperLogin.service.UserService;
import com.retail.shopper.SmartShopperLogin.service.UserServiceImpl;
import com.retail.shopper.SmartShopperLogin.util.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api/v1")
public class RegisterUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Utils utils;
	
	/**
	 * Register new User. This is the only operation users will be interested. 
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	@PostMapping("/users/register")
	public String createUser(@Valid @RequestBody User user) {
		try {
			user.setCreatedAt(new Date());
			userRepository.save(user);
			utils.sendEmail("Registration email","User "+user.getUserName()+" registered successfully with smartshopper");
		} catch (Exception e) {
			utils.getPrintableStackTrace(e);
			logger.info("User Registration Failing: " + utils.getPrintableStackTrace(e));
			return "Unable to register user. UserName and Email combination already exists";
		}
		
		return "User Registered Successfully";
	}
	
	// For Admin usage. Get all users
	/**
	 * Get all users list.
	 *
	 * @return the list
	 */
	@GetMapping("/users/register")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	// For Admin usage. Get User by id
	/**
	 * Gets Registered user by id.
	 *
	 * @param userId
	 *            the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException
	 *             the resource not found exception
	 */
	@GetMapping("/users/register/{id}")
	public ResponseEntity<User> getUsersById(
			@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User not found on :: "
						+ userId));
		return ResponseEntity.ok().body(user);
	}	

	
	// For Admin usage. Update user
	/**
	 * Update user response entity.
	 *
	 * @param userId
	 *            the user id
	 * @param userDetails
	 *            the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException
	 *             the resource not found exception
	 */
	@PutMapping("/users/register/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User not found on :: "
						+ userId));
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setUserName(userDetails.getUserName());
		user.setPassword(userDetails.getPassword());
		user.setPhone(userDetails.getPhone());
		user.setCreatedAt(new Date());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	//For Admin users. Delete user
	/**
	 * Delete user map.
	 *
	 * @param userId
	 *            the user id
	 * @return the map
	 * @throws Exception
	 *             the exception
	 */
	@DeleteMapping("/user/register/{id}")
	public Map<String, Boolean> deleteUser(
			@PathVariable(value = "id") Long userId) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User not found on :: "
						+ userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

}
