package com.tweetapp.service;


import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.UserResponse;

public interface LoginService {

	UserResponse registerUser(RegisterRequest request);
	
	UserResponse loginUser(LoginRequest request);
	
	UserResponse forgotPassword(ForgotPasswordRequest request, String username);

	//void deleteUser(String id);

	UserResponse getAllUsers();

	UserResponse searchByUserName(String username);
}
