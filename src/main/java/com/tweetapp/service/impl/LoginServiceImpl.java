package com.tweetapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.controller.LoginController;
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

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	private static final String NAME = "LoginServiceImpl";
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserResponse registerUser(RegisterRequest request) {
		
		LOGGER.debug("registeruser service - saveUser start " + request.getRequestHeader().getTransactionId());
		
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		boolean isEmailPresent = false;
		boolean isLoginIdPresent =false;
		isLoginIdPresent =userRepo.existsByLoginId(request.getLoginId());
		isEmailPresent = userRepo.existsByEmailId(request.getEmailId());
		
		if(isLoginIdPresent || isEmailPresent) {
			
			if(isEmailPresent) {
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.EMAIL_IN_USE_CODE,TweetAppConstants.EMAIL_IN_USE_MSG,TweetAppConstants.EMAIL_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"2","WARNING",messages);
			}else {
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.LOGINID_IN_USE_CODE,TweetAppConstants.LOGINID_IN_USE_MSG,TweetAppConstants.LOGINID_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"2","WARNING",messages);
			}
		}else {
			User user = userRepo.save(TweetAppServiceUtil.registerUser(request));
			if(user!=null) {
				TweetAppServiceUtil.populateMessages(messages,"200","Success","Registration Success : "+ user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			}else {
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,TweetAppConstants.INTERNAL_SERVER_ERROR_MSG,"Registration Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
			}
		}
		
		LOGGER.debug("registeruser service - saveUser end " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification().setTransactionId(request.getRequestHeader().getTransactionId());
		
		return response;
	}

	@Override
	public UserResponse loginUser(LoginRequest request) {
		LOGGER.debug("Login service - start " + request.getRequestHeader().getTransactionId());
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		User user = userRepo.findByLoginId(request.getLoginID());
		if(user!=null) {
			
			if(TweetAppServiceUtil.validatePassword(request.getPassword(),user.getPassword())) {
				TweetAppServiceUtil.populateMessages(messages,"200","Success","Login Success : "+ user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			}else {
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INVALID_PASSWORD_CODE,TweetAppConstants.INVALID_PASSWORD,TweetAppConstants.INVALID_PASSWORD);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
			}
			
		}else {
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
		}
		LOGGER.debug("Login service - end " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification().setTransactionId(request.getRequestHeader().getTransactionId());
		
		return response;
		
	}

	@Override
	public UserResponse forgotPassword(ForgotPasswordRequest request, String username) {
		LOGGER.debug("forgotPassword service - start " + request.getRequestHeader().getTransactionId());
		UserResponse response = new UserResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		response.setResponseHeader(responseHeader);
		List<Message> messages = new ArrayList<>();
		User user = userRepo.findByLoginId(username);
		if(user!=null) {
			user.setPassword(TweetAppServiceUtil.encodePassword(request.getPassword()));
			User u = userRepo.save(user);
			if(u!=null) {
				TweetAppServiceUtil.populateMessages(messages,"200","Success","Password Updated : "+ u.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			}else {
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,TweetAppConstants.INTERNAL_SERVER_ERROR_MSG,"Password Reset Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
			}
		}else {
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(responseHeader,"1","FAILURE",messages);
		}
		LOGGER.debug("forgotPassword service - end " + request.getRequestHeader().getTransactionId());
		response.getResponseHeader().getTransactionNotification().setTransactionId(request.getRequestHeader().getTransactionId());
		return response;
	}

//	@Override
//	public void deleteUser(String id) {
//		userRepo.deleteById(id);
//	}

	@Override
	public UserResponse getAllUsers() {
		LOGGER.debug(NAME +" getAllUsers start");
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		List<User> userList = userRepo.findAll();
		if(userList!=null && !userList.isEmpty()) {
			List<UserDTO> userDTOList = TweetAppServiceUtil.userDtoMapper(userList);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			response.setData(userDTOList);
		}else {
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
		}
		
		LOGGER.debug(NAME +" getAllUsers end");
		return response;
	}

	@Override
	public UserResponse searchByUserName(String username) {
		LOGGER.debug(NAME +" searchByUserName start "+ username);
		UserResponse response = new UserResponse();
		List<Message> messages = new ArrayList<>();
		List<User> userList = userRepo.searchUserByUsername(username);
		if(userList!=null && !userList.isEmpty()) {
			
			List<UserDTO> userDTOList = TweetAppServiceUtil.userDtoMapper(userList);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			response.setData(userDTOList);
		}else {
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
		}
		
		LOGGER.debug(NAME +" searchByUserName end "+ username);
		return response;

	}
	
}
