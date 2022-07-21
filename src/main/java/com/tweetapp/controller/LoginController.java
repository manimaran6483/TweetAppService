package com.tweetapp.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.ForgotPasswordResponse;
import com.tweetapp.model.response.LoginResponse;
import com.tweetapp.model.response.RegisterResponse;
import com.tweetapp.service.LoginService;

@RestController
public class LoginController {

	private LoginService loginService;
	
	@Autowired
	private LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	private static final String NAME = "Login-Controller-Log";
	
	
	@PostMapping(TweetAppConstants.REGISTER_PATH)
	private ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug( NAME + ": registeruser - start" + transactionId);
		RegisterResponse response = new RegisterResponse();
		try {
			response = loginService.registerUser(request);
		}catch(Exception e) {
			
		}
		
		LOGGER.debug( NAME + ": registeruser - end" + transactionId);
		return new ResponseEntity<RegisterResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping(TweetAppConstants.LOGIN_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug( NAME + ": loginUser - start" + transactionId);
		LoginResponse response = new LoginResponse();
		try {
			response = loginService.loginUser(request);
		}catch(Exception e) {
			
		}
		
		LOGGER.debug( NAME + ": loginUser - end" + transactionId);
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping(TweetAppConstants.FORGOTPASSWORD_PATH)
	private ResponseEntity<ForgotPasswordResponse> forgotPassword(@PathVariable String username, @Valid @RequestBody ForgotPasswordRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug( NAME + ": forgotPassword - start" + transactionId);
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		try {
			response = loginService.forgotPassword(request,username);
		}catch(Exception e) {
			
		}
		
		LOGGER.debug( NAME + ": forgotPassword - end" + transactionId);
		return new ResponseEntity<ForgotPasswordResponse>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String> deleteId(@PathVariable String id){
		
		loginService.deleteUser(id);
		
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	
}
