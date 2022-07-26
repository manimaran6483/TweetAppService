package com.tweetapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.response.ReplyResponse;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.service.TweetService;

/**
 * This class configures rest endpoints w.r.t Tweet
 * 
 * @author Manimaran
 *
 */
@RestController
@CrossOrigin
public class TweetController {

	@Autowired
	private static final String NAME = "TweetController-Log";

	@Autowired
	private TweetService tweetService;

	private static final Logger log = LoggerFactory.getLogger(TweetController.class);
	
	/**
	REST URL to retrive all tweets
	@param transactionId
	*/
	@GetMapping(TweetAppConstants.GET_ALL_TWEETS_PATH)
	private ResponseEntity<TweetResponse> getAllTweets(@RequestHeader("transactionId") String transactionId) {
		log.debug( NAME + ": getAllTweets - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getAllTweets();
		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": getAllTweets - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	REST URL to retrive all tweets for user
	@param transactionId
	@param username
	*/
	@GetMapping(TweetAppConstants.GET_TWEETS_OF_USER_PATH)
	private ResponseEntity<TweetResponse> getAllTweetsForUser(@PathVariable(name = "username") String username,
			@RequestHeader("transactionId") String transactionId) {
		log.debug( NAME + ": getAllTweets - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getTweetsForUser(username);
		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": getAllTweets - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	REST URL to post a tweet
	@param username
	@param request
	*/
	@PostMapping(TweetAppConstants.POST_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<TweetResponse> postTweet(@PathVariable(name = "username") String username,
			@Valid @RequestBody TweetRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		log.debug( NAME + ": postTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.postTweet(request);
		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": postTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	/**
	REST URL to update tweet
	@param username
	@param tweetId
	@param request
	*/
	@PutMapping(TweetAppConstants.UPDATE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<TweetResponse> updateTweet(@PathVariable(name = "username") String username,
			@PathVariable(name = "id") String tweetId, @Valid @RequestBody TweetRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		log.debug( NAME + ": updateTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.updateTweet(request, tweetId);
		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": updateTweet - end  - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	REST URL to delete a tweet
	@param transactionId
	@param tweetId
	@param username
	*/
	@DeleteMapping(TweetAppConstants.DELETE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> deleteTweet(@PathVariable(name = "username") String username,
			@PathVariable(name = "id") String tweetId, @RequestHeader("transactionId") String transactionId) {
		log.debug( NAME + ": deleteTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.deleteTweet(tweetId);
		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": deleteTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	REST URL to like a tweet
	@param tweetId
	@param request
	*/
	@PostMapping(TweetAppConstants.LIKE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> likeTweet(@PathVariable("id") String tweetId,
			@Valid @RequestBody LikeRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		log.debug( NAME + ": likeTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			if (request.getLikeFlag().equalsIgnoreCase("Y")) {
				response = tweetService.likeTweet(request);
			} else {
				response = tweetService.unlikeTweet(request);
			}

		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME +" - " +  e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": likeTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	REST URL to reply a tweet
	@param tweetId
	@param request
	*/
	@PostMapping(TweetAppConstants.REPLY_TWEET)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> replyTweet(@PathVariable("id") String tweetId,
			@Valid @RequestBody ReplyRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		log.debug( NAME + ": replyTweet - start - " + transactionId);
		ReplyResponse response = new ReplyResponse();
		try {
			response = tweetService.replyTweet(request);

		} catch (Exception e) {
			log.debug( "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		log.debug( NAME + ": replyTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
