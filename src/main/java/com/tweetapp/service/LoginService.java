package com.tweetapp.service;

import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.ForgotPasswordResponse;
import com.tweetapp.model.response.LoginResponse;
import com.tweetapp.model.response.RegisterResponse;

public interface LoginService {

	RegisterResponse registerUser(RegisterRequest request);
	
	LoginResponse loginUser(LoginRequest request);
	
	ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request, String username);

	void deleteUser(String id);
}
