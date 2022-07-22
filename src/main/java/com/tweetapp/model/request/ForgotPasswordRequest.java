package com.tweetapp.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tweetapp.model.common.composite.RequestHeader;

public class ForgotPasswordRequest {

	private RequestHeader requestHeader;

	@NotNull(message = "Mandatory Field - password should not be blank")
	@NotBlank(message = "Mandatory Field - password should not be blank")
	private String password;
	
	@NotNull(message = "Mandatory Field - confirmPassword should not be blank")
	@NotBlank(message = "Mandatory Field - confirmPassword should not be blank")
	private String confirmPassword;
	
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
