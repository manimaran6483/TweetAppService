package com.tweetapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tweetapp.model.common.composite.RequestHeader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
	
	
	
}
