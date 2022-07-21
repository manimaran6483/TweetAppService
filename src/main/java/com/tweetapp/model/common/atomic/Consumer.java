package com.tweetapp.model.common.atomic;

public class Consumer {

	private String id;
	private String name;
	private String businessTransactionType;
	private String type;
	private String requestDateTime;
	private String hostName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusinessTransactionType() {
		return businessTransactionType;
	}
	public void setBusinessTransactionType(String businessTransactionType) {
		this.businessTransactionType = businessTransactionType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	
}
