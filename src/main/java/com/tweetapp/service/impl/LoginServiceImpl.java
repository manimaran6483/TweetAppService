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
import com.tweetapp.model.response.ForgotPasswordResponse;
import com.tweetapp.model.response.LoginResponse;
import com.tweetapp.model.response.RegisterResponse;
import com.tweetapp.repository.LoginRepository;
import com.tweetapp.service.LoginService;
import com.tweetapp.util.TweetAppServiceUtil;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private LoginRepository loginRepo;
	
	@Override
	public RegisterResponse registerUser(RegisterRequest request) {
		
		LOGGER.debug("registeruser service - saveUser start " + request.getRequestHeader().getTransactionId());
		
		RegisterResponse response = new RegisterResponse();
		List<Message> messages = new ArrayList<>();
		boolean isEmailPresent = false;
		boolean isLoginIdPresent =false;
		isLoginIdPresent =loginRepo.existsByLoginId(request.getLoginId());
		isEmailPresent = loginRepo.existsByEmailId(request.getEmailId());
		
		if(isLoginIdPresent || isEmailPresent) {
			
			if(isEmailPresent) {
				response.setStatus(TweetAppConstants.EMAIL_IN_USE_MSG);
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.EMAIL_IN_USE_CODE,TweetAppConstants.EMAIL_IN_USE_MSG,TweetAppConstants.EMAIL_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"2","WARNING",messages);
			}else {
				response.setStatus(TweetAppConstants.LOGINID_IN_USE_MSG);
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.LOGINID_IN_USE_CODE,TweetAppConstants.LOGINID_IN_USE_MSG,TweetAppConstants.LOGINID_IN_USE_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"2","WARNING",messages);
			}
		}else {
			User user = loginRepo.save(TweetAppServiceUtil.registerUser(request));
			if(user!=null) {
				response.setStatus("Registration Success : "+ user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			}else {
				response.setStatus("Registration Failed");
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,TweetAppConstants.INTERNAL_SERVER_ERROR_MSG,"Registration Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
			}
		}
		
		LOGGER.debug("registeruser service - saveUser end " + request.getRequestHeader().getTransactionId());
		
		
		return response;
	}

	@Override
	public LoginResponse loginUser(LoginRequest request) {
		LOGGER.debug("Login service - start " + request.getRequestHeader().getTransactionId());
		LoginResponse response = new LoginResponse();
		List<Message> messages = new ArrayList<>();
		User user = loginRepo.findByLoginId(request.getLoginID());
		if(user!=null) {
			
			if(TweetAppServiceUtil.validatePassword(request.getPassword(),user.getPassword())) {
				response.setStatus("Login Success : "+ user.getUserId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			}else {
				response.setStatus("Login Failed");
				TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INVALID_PASSWORD_CODE,TweetAppConstants.INVALID_PASSWORD,TweetAppConstants.INVALID_PASSWORD);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
			}
			
		}else {
			response.setStatus("Login Failed");
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
		}
		LOGGER.debug("Login service - end " + request.getRequestHeader().getTransactionId());
		
		
		return response;
		
	}

	@Override
	public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request, String username) {
		LOGGER.debug("forgotPassword service - start " + request.getRequestHeader().getTransactionId());
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		response.setResponseHeader(responseHeader);
		List<Message> messages = new ArrayList<>();
		if(loginRepo.existsByLoginId(username)) {
			
			// update password
			
			response.setStatus("User Found : ");
			TweetAppServiceUtil.populateResponseHeader(responseHeader,"0","SUCCESS",messages);
		}else {
			response.setStatus("User Not Found");
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(responseHeader,"1","FAILURE",messages);
		}
		LOGGER.debug("forgotPassword service - end " + request.getRequestHeader().getTransactionId());
		
		return response;
	}

	@Override
	public void deleteUser(String id) {
		loginRepo.deleteById(id);
	}

}
