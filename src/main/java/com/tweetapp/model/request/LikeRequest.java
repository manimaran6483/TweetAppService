package com.tweetapp.model.request;

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
public class LikeRequest {
	
	private RequestHeader requestHeader;
	
	private String likeFlag;
	
	private String userName;
	
	private String tweetId;

	private String likeId;


}
