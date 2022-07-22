package com.tweetapp.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tweetapp.model.common.composite.RequestHeader;

public class LoginRequest {

	private RequestHeader requestHeader;

	@NotNull(message = "Mandatory Field - loginID should not be blank")
	@NotBlank(message = "Mandatory Field - loginID should not be blank")
	private String loginID;
	
	@NotNull(message = "Mandatory Field - password should not be blank")
	@NotBlank(message = "Mandatory Field - password should not be blank")
	private String password;
	
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
