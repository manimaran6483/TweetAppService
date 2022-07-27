package com.tweetapp.model.request;

import com.tweetapp.model.common.composite.RequestHeader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequest {
	
	private RequestHeader requestHeader;
	
	private String likeFlag;
	
	private String userName;
	
	private String tweetId;

	private String likeId;


}
