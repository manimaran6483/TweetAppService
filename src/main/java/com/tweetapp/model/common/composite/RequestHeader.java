package com.tweetapp.model.common.composite;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tweetapp.model.common.atomic.Consumer;

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
public class RequestHeader {

	private Consumer consumer = new Consumer();
	
	@NotNull(message = "Mandatory Field - transactionId should not be null")
	@NotBlank(message = "Mandatory Field - transactionId should not be blank")
	private String transactionId;
	

}
