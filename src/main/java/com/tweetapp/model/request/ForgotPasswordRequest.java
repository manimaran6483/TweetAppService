package com.tweetapp.model.request;

import com.tweetapp.model.common.composite.RequestHeader;

public class ForgotPasswordRequest {

	private RequestHeader requestHeader;

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
}
