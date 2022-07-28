package com.tweetapp.model.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class ForgotPasswordRequest {

	private RequestHeader requestHeader;

	@NotNull(message = "Mandatory Field - password should not be null")
	@NotBlank(message = "Mandatory Field - password should not be blank")
	private String password;
	
	@NotNull(message = "Mandatory Field - confirmPassword should not be null")
	@NotBlank(message = "Mandatory Field - confirmPassword should not be blank")
	private String confirmPassword;
	
}
