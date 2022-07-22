package com.tweetapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tweetapp.model.common.composite.RequestHeader;

public class RegisterRequest {

	private RequestHeader requestHeader;
	
	@NotNull(message = "Mandatory Field - FirstName should not be null")
	@NotBlank(message = "Mandatory Field - FirstName should not be blank")
	private String firstName;
	
	@NotNull(message = "Mandatory Field - LastName should not be null")
	@NotBlank(message = "Mandatory Field - LastName should not be blank")
	private String lastName;
	
	@NotNull(message = "Mandatory Field - Email should not be null")
	@NotBlank(message = "Mandatory Field - Email should not be blank")
	@Email
	private String emailId;

	@NotNull(message = "Mandatory Field - LoginID should not be null")
	@NotBlank(message = "Mandatory Field - LoginID should not be blank")
	@Size(max = 15,min = 8,message = "Mandatory Field - Length should be between 8 and 15")
	private String loginId;

	@NotNull(message = "Mandatory Field - Password should not be null")
	@NotBlank(message = "Mandatory Field - Password should not be blank")
	private String password;

	@NotNull(message = "Mandatory Field - ConfirmPassword should not be null")
	@NotBlank(message = "Mandatory Field - ConfirmPassword should not be blank")
	private String confirmPassword;

	@NotNull(message = "Mandatory Field - ContactNumber should not be blank")
	@NotBlank(message = "Mandatory Field - ContactNumber should not be blank")
	@Size(max = 10,min = 10,message = "Mandatory Field - Length should be 10")
	private String contactNumber;
	
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
}
