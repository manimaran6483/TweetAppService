package com.tweetapp.model.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tweetapp.model.common.composite.RequestHeader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TweetRequest {

	private RequestHeader requestHeader;
	
	@NotNull(message = "Mandatory Field - tweet should not be null")
	@NotBlank(message = "Mandatory Field - tweet should not be blank")
	private String tweet;

	@NotNull(message = "Mandatory Field - tag should not be null")
	@NotBlank(message = "Mandatory Field - tag should not be blank")
	private String tag;

	@NotNull(message = "Mandatory Field - userId should not be null")
	@NotBlank(message = "Mandatory Field - userId should not be blank")
	private String userId;


}
