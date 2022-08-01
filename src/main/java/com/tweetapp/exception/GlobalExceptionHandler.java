package com.tweetapp.exception;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.util.TweetAppServiceUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	/**
	 * 
	 *	provides custom exception message for MethodArgumentNotValidException
	 *	@param methodArgumentNotValidException 
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		String error = methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		List<Message> messages = new ArrayList<Message>();
		if(methodArgumentNotValidException.getMessage().contains("LoginController")) {
			UserResponse response = new UserResponse();
			TweetAppServiceUtil.populateMessages(messages, "400", "Bad Request", error);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "2", "WARNING", messages);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			TweetResponse response = new TweetResponse();
			TweetAppServiceUtil.populateMessages(new ArrayList<Message>(), "400", "Bad Request", error);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "2", "WARNING", messages);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * 
	 *	provides custom exception message for MissingRequestHeaderException
	 *	@param missingRequestHeaderException 
	*/
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<Object> handleMissingHeaderException(MissingRequestHeaderException missingRequestHeaderException){
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<Message>();
		TweetAppServiceUtil.populateMessages(messages, "400", "Bad Request", missingRequestHeaderException.getMessage());
		TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "2", "WARNING", messages);
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Message", 
	      "No transaction Id");

	    return ResponseEntity.badRequest()
	      .headers(responseHeaders)
	      .body(response);
	}
	
}
