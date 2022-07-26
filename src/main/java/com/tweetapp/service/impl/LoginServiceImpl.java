package com.tweetapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.request.UserDTO;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.LoginService;
import com.tweetapp.util.TweetAppServiceUtil;


/**
 * This class provides business logic for User functionalities
 * 
 * @author Manimaran
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	private static final String NAME = "LoginServiceImpl-Log";

	@Autowired
	private UserRepository userRepo;

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	/**
	 * 
	 *	call user repository to register a user
	 *	@param request
	*/
	@Override
	public UserResponse registerUser(RegisterRequest request) {

		log.debug( NAME + " : registeruser service - saveUser start - " + request.getRequestHeader().getTransactionId());

		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		boolean isEmailPresent = false;
		boolean isLoginIdPresent = false;
		isLoginIdPresent = userRepo.existsByLoginId(request.getLoginId());
		isEmailPresent = userRepo.existsByEmailId(request.getEmailId());

		if (isLoginIdPresent || isEmailPresent) {

			if (isEmailPresent) {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.EMAIL_IN_USE_CODE,
						TweetAppConstants.EMAIL_IN_USE_MSG, TweetAppConstants.EMAIL_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "2", "WARNING", messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.LOGINID_IN_USE_CODE,
						TweetAppConstants.LOGINID_IN_USE_MSG, TweetAppConstants.LOGINID_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "2", "WARNING", messages);
			}
		} else {
			User user = userRepo.save(TweetAppServiceUtil.registerUser(request));
			if (user != null) {
				List<User> userList = new ArrayList<>();
				userList.add(user);
				response.setData(TweetAppServiceUtil.userDtoMapper(userList));
				TweetAppServiceUtil.populateMessages(messages, "200", "Success",
						"Registration Success : " + user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			} else {
				
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Registration Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		}

		log.debug( NAME + " : registeruser service - saveUser end - " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification()
				.setTransactionId(request.getRequestHeader().getTransactionId());

		return response;
	}

	/**
	 * 
	 *	call user repository to login a user
	 *	@param request
	*/
	@Override
	public UserResponse loginUser(LoginRequest request) {
		log.debug( NAME + " : Login service - start - " + request.getRequestHeader().getTransactionId());
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		User user = userRepo.findByLoginId(request.getLoginID());
		if (user != null) {

			if (TweetAppServiceUtil.validatePassword(request.getPassword(), user.getPassword())) {
				List<User> userList = new ArrayList<>();
				userList.add(user);
				response.setData(TweetAppServiceUtil.userDtoMapper(userList));
				TweetAppServiceUtil.populateMessages(messages, "200", "Success", "Login Success : " + user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INVALID_PASSWORD_CODE,
						TweetAppConstants.INVALID_PASSWORD, TweetAppConstants.INVALID_PASSWORD);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}

		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}
		log.debug( NAME + " : Login service - end - " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification()
				.setTransactionId(request.getRequestHeader().getTransactionId());

		return response;

	}

	/**
	 * 
	 *	call user repository to change password for a user
	 *	@param request
	 *  @param username
	*/
	@Override
	public UserResponse forgotPassword(ForgotPasswordRequest request, String username) {
		log.debug( NAME + " : forgotPassword service - start - " + request.getRequestHeader().getTransactionId());
		UserResponse response = new UserResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		response.setResponseHeader(responseHeader);
		List<Message> messages = new ArrayList<>();
		User user = userRepo.findByLoginId(username);
		if (user != null) {
			user.setPassword(TweetAppServiceUtil.encodePassword(request.getPassword()));
			User u = userRepo.save(user);
			if (u != null) {
				TweetAppServiceUtil.populateMessages(messages, "200", "Success", "Password Updated : " + u.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Password Reset Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(responseHeader, "1", "FAILURE", messages);
		}
		log.debug( NAME + " : forgotPassword service - end - " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification()
				.setTransactionId(request.getRequestHeader().getTransactionId());
		return response;
	}
	
	/**
	 * 
	 *	call user repository to retrieve all users
	 *	
	*/
	@Override
	public UserResponse getAllUsers() {
		log.debug( NAME + " getAllUsers start");
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		List<User> userList = userRepo.findAll();
		if (userList != null && !userList.isEmpty()) {
			List<UserDTO> userDTOList = TweetAppServiceUtil.userDtoMapper(userList);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			response.setData(userDTOList);
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		log.debug( NAME + " getAllUsers end");
		return response;
	}

	
	/**
	 * 
	 *	call user repository to search a user
	 *	@param username
	*/
	@Override
	public UserResponse searchByUserName(String username) {
		log.debug( NAME + " searchByUserName start - " + username);
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		List<User> userList = userRepo.searchByUsername(username);
		if (userList != null && !userList.isEmpty()) {

			List<UserDTO> userDTOList = TweetAppServiceUtil.userDtoMapper(userList);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			response.setData(userDTOList);
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		log.debug( NAME + " searchByUserName end - " + username);
		return response;

	}

}
