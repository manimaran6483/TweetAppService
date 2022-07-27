package com.tweetapp.service;

import javax.validation.Valid;

import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.response.ReplyResponse;
import com.tweetapp.model.response.TweetResponse;

public interface TweetService {

	TweetResponse getAllTweets();
	
	TweetResponse getTweetsForUser(String username);
	
	TweetResponse postTweet(TweetRequest request);
	
	TweetResponse updateTweet(TweetRequest request,String tweetId);
	
	TweetResponse deleteTweet(String tweetId);
	
	TweetResponse likeTweet(LikeRequest reqeust);
	
	ReplyResponse replyTweet(ReplyRequest request);

	TweetResponse unlikeTweet(@Valid LikeRequest request);
}
