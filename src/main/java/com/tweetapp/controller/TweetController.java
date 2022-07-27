package com.tweetapp.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

@RestController
public class TweetController {

	private static final Logger LOGGER = LogManager.getLogger(TweetController.class);

	private static final String NAME = "TweetController";

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}

	private TweetService tweetService;

	@GetMapping(TweetAppConstants.GET_ALL_TWEETS_PATH)
	private ResponseEntity<TweetResponse> getAllTweets(@RequestHeader("transactionId") String transactionId) {
		LOGGER.debug(NAME + ": getAllTweets - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getAllTweets();
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": getAllTweets - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(TweetAppConstants.GET_TWEETS_OF_USER_PATH)
	private ResponseEntity<TweetResponse> getAllTweetsForUser(@PathVariable(name = "username") String username,@RequestHeader("transactionId") String transactionId) {
		LOGGER.debug(NAME + ": getAllTweets - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getTweetsForUser(username);
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": getAllTweets - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(TweetAppConstants.POST_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<TweetResponse> postTweet(@PathVariable(name = "username") String username, @Valid @RequestBody TweetRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug(NAME + ": postTweet - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.postTweet(request);
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": postTweet - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PutMapping(TweetAppConstants.UPDATE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<TweetResponse> updateTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId,
			@Valid @RequestBody TweetRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug(NAME + ": updateTweet - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.updateTweet(request,tweetId);
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": updateTweet - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@DeleteMapping(TweetAppConstants.DELETE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> deleteTweet(@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId,
			@RequestHeader("transactionId") String transactionId) {
		LOGGER.debug(NAME + ": deleteTweet - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.deleteTweet(tweetId);
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": deleteTweet - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
	}

	@PostMapping(TweetAppConstants.LIKE_TWEET_PATH)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> likeTweet(@PathVariable("id") String tweetId,@Valid @RequestBody LikeRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug(NAME + ": likeTweet - start " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			if(request.getLikeFlag().equalsIgnoreCase("Y")){
				response = tweetService.likeTweet(request);
			}else {
				response = tweetService.unlikeTweet(request);
			}
			
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": likeTweet - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PostMapping(TweetAppConstants.REPLY_TWEET)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private ResponseEntity<Object> replyTweet(@PathVariable("id") String tweetId,@Valid @RequestBody ReplyRequest request) {
		String transactionId = request.getRequestHeader().getTransactionId();
		LOGGER.debug(NAME + ": replyTweet - start " + transactionId);
		ReplyResponse response = new ReplyResponse();
		try {
			response = tweetService.replyTweet(request);
			
		} catch (Exception e) {
			LOGGER.debug("Exception Occurred in " + NAME + e.getMessage() + transactionId);
		}

		LOGGER.debug(NAME + ": replyTweet - end " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

}
