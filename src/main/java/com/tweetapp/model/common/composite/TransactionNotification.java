package com.tweetapp.model.common.composite;

import java.util.Date;

public class TransactionNotification {

	private String status;
	private String statusCode;
	private Date responseDateTime;
	private String transactionId;
	private Remarks remarks = new Remarks();
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getResponseDateTime() {
		return responseDateTime;
	}
	public void setResponseDateTime(Date responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Remarks getRemarks() {
		return remarks;
	}
	public void setRemarks(Remarks remarks) {
		this.remarks = remarks;
	}
	
	
}
