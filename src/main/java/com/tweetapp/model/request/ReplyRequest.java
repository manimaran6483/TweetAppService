package com.tweetapp.model.request;

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
public class ReplyRequest {

	private RequestHeader requestHeader;

	private String comment;
	
	private String tag;

	private String userName;

	private String tweetId;


}
