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
		
		RegisterResponse response = new RegisterResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		response.setResponseHeader(responseHeader);
		List<Message> messages = new ArrayList<>();
		User user = loginRepo.save(TweetAppServiceUtil.registerUser(request));
		if(user!=null) {
			response.setStatus("Registration Success : "+ user.getUserId());
			TweetAppServiceUtil.populateResponseHeader(responseHeader,"0","SUCCESS",messages);
		}else {
			response.setStatus("Registration Failed");
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,TweetAppConstants.INTERNAL_SERVER_ERROR_MSG,"Registration Failed");
			TweetAppServiceUtil.populateResponseHeader(responseHeader,"1","FAILURE",messages);
		}
		LOGGER.debug("registeruser - service - savedUser : " + user);
		
		
		return response;
	}

	@Override
	public LoginResponse loginUser(LoginRequest request) {
		return null;
	}

	@Override
	public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request, String username) {
		return null;
	}

	@Override
	public void deleteUser(String id) {
		loginRepo.deleteById(id);
	}

}
