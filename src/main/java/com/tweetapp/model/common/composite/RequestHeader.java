package com.tweetapp.model.common.composite;

import com.tweetapp.model.common.atomic.Consumer;

public class RequestHeader {

	private Consumer consumer = new Consumer();
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
