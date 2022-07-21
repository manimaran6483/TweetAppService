package com.tweetapp.model.common.composite;

public class ResponseHeader {

	private TransactionNotification transactionNotification = new TransactionNotification();

	public TransactionNotification getTransactionNotification() {
		return transactionNotification;
	}

	public void setTransactionNotification(TransactionNotification transactionNotification) {
		this.transactionNotification = transactionNotification;
	}
	
	
}
