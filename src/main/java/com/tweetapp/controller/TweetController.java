package com.tweetapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.constants.TweetAppConstants;

@RestController
public class TweetController {

	
	@GetMapping(TweetAppConstants.GET_ALL_TWEETS_PATH)
	private ResponseEntity<Object> getAllTweets(){
		
		return null;
	}
	
	@GetMapping(TweetAppConstants.GET_ALL_USERS_PATH)
	private ResponseEntity<Object> getAllUsers(){
		
		return null;
	}
	
	@GetMapping(TweetAppConstants.SEARCH_USER_PATH)
	private ResponseEntity<Object> searchUserByLoginId(){
		
		return null;
	}
	
	@GetMapping(TweetAppConstants.GET_TWEETS_OF_USER_PATH)
	private ResponseEntity<Object> getAllTweetsForUser(){
		
		return null;
	}
	
	@PostMapping(TweetAppConstants.POST_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> postTweet(){
		
		return null;
	}
	
	@PostMapping(TweetAppConstants.UPDATE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> updateTweet(){
		
		return null;
	}
	@PostMapping(TweetAppConstants.DELETE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> deleteTweet(){
		
		return null;
	}
	@PostMapping(TweetAppConstants.LIKE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> likeTweet(){
		
		return null;
	}
	@PostMapping(TweetAppConstants.REPLY_TWEET)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> replyTweet(){
		
		return null;
	}
	
	
	
	
}
