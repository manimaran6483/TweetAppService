package com.tweetapp.model.common.composite;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tweetapp.model.common.atomic.Consumer;

public class RequestHeader {

	private Consumer consumer = new Consumer();
	
	@NotNull(message = "Mandatory Field - transactionId should not be null")
	@NotBlank(message = "Mandatory Field - transactionId should not be blank")
	private String transactionId;
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	

}
