package com.tweetapp.model.response;

import com.tweetapp.model.common.composite.ResponseHeader;

public class ForgotPasswordResponse {

	private ResponseHeader responseHeader = new ResponseHeader();

	private String status;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
