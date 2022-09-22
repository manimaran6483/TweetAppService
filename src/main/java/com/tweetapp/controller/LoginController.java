package com.tweetapp.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.service.LoginService;


/**
 * This class configures rest endpoints w.r.t User
 * 
 * @author Manimaran
 *
 */

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${tweetapp.kafka.topic}")
	private String TOPIC = "";
	
	private static final String NAME = "LoginController-Log";
	
	/**
	REST URL to register a user
	@param request
	*/
	@PostMapping(TweetAppConstants.REGISTER_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		kafkaTemplate.send(TOPIC, NAME + " : registeruser - start - " + transactionId);
		UserResponse response = new UserResponse();
		try {
			kafkaTemplate.send(TOPIC,NAME + " -  Request : " + new ObjectMapper().writeValueAsString(request));
			response = loginService.registerUser(request);
		}catch(Exception e) {
			kafkaTemplate.send(TOPIC,"Exception Occurred in "+ NAME +" - " + e.getMessage() +" - " + transactionId);
		}
		
		kafkaTemplate.send(TOPIC, NAME + " : registeruser - end - " + transactionId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
	/**
	REST URL to login a user
	@param request
	*/
	@PostMapping(TweetAppConstants.LOGIN_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		kafkaTemplate.send(TOPIC, NAME + " : loginUser - start - " + transactionId);
		UserResponse response = new UserResponse();
		kafkaTemplate.send(TOPIC,loginService.toString());
		try {
			kafkaTemplate.send(TOPIC,NAME + " Request : " + new ObjectMapper().writeValueAsString(request));
			response = loginService.loginUser(request);
		}catch(Exception e) {
			kafkaTemplate.send(TOPIC,"Exception Occurred in "+ NAME +" - " + e.getMessage() + " - " +transactionId);
		}
		
		kafkaTemplate.send(TOPIC, NAME + ": loginUser - end - " + transactionId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
	/**
	REST URL to change password
	@param request
	@param username
	*/
	@PostMapping(TweetAppConstants.FORGOTPASSWORD_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<UserResponse> forgotPassword(@PathVariable String username, @Valid @RequestBody ForgotPasswordRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		kafkaTemplate.send(TOPIC, NAME + " : forgotPassword - start - " + transactionId);
		UserResponse response = new UserResponse();
		try {
			kafkaTemplate.send(TOPIC,NAME + " Request : " + new ObjectMapper().writeValueAsString(request));
			response = loginService.forgotPassword(request,username);
		}catch(Exception e) {
			kafkaTemplate.send(TOPIC,"Exception Occurred in "+ NAME +" - " + e.getMessage() +" - " + transactionId);
		}
		
		kafkaTemplate.send(TOPIC, NAME + ": forgotPassword - end - " + transactionId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
	/**
	REST URL to retrieve all users
	@param transactionId
	*/
	@GetMapping(TweetAppConstants.GET_ALL_USERS_PATH)
	private ResponseEntity<UserResponse> getAllUsers(@RequestHeader("transactionId") String transactionId) {
		kafkaTemplate.send(TOPIC, NAME + ": getAllUsers - start - " + transactionId);
		UserResponse response = new UserResponse();
		try {
			 response = loginService.getAllUsers();
		}catch(Exception e) {
			kafkaTemplate.send(TOPIC,"Exception Occurred in "+ NAME + " - " + e.getMessage() + " - " + transactionId);
		}
		
		kafkaTemplate.send(TOPIC, NAME + ": getAllUsers - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

	
	/**
	REST URL to search a user
	@param username
	@param username
	*/
	@GetMapping(TweetAppConstants.SEARCH_USER_PATH)
	private ResponseEntity<UserResponse> searchUserByLoginId(@PathVariable(name = "username") String username,@RequestHeader("transactionId") String transactionId) {
		kafkaTemplate.send(TOPIC, NAME + ": searchUserByLoginId - start - " + transactionId);
		UserResponse response = new UserResponse();
		try {
			kafkaTemplate.send(TOPIC,NAME + " Username from path : " + username);
			 response = loginService.searchByUserName(username);
		}catch(Exception e) {
			kafkaTemplate.send(TOPIC,e.toString());
			kafkaTemplate.send(TOPIC,"Exception Occurred in "+ NAME + " - " + " - " +e.getMessage() + " - " + transactionId);
		}
		
		kafkaTemplate.send(TOPIC, NAME + ": searchUserByLoginId - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
