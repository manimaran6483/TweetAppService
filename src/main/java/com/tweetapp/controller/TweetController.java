package com.tweetapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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

/**
 * This class configures rest endpoints w.r.t Tweet
 * 
 * @author Manimaran
 *
 */
@RestController
public class TweetController {

	@Autowired
	private static final String NAME = "TweetController-Log";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${tweetapp.kafka.topic}")
	private String TOPIC = "";

	private TweetService tweetService;

	/**
	REST URL to retrive all tweets
	@param transactionId
	*/
	@GetMapping(TweetAppConstants.GET_ALL_TWEETS_PATH)
	private ResponseEntity<TweetResponse> getAllTweets(@RequestHeader("transactionId") String transactionId) {
		kafkaTemplate.send(TOPIC, NAME + ": getAllTweets - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getAllTweets();
		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": getAllTweets - end - " + transactionId);
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
		kafkaTemplate.send(TOPIC, NAME + ": getAllTweets - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.getTweetsForUser(username);
		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": getAllTweets - end - " + transactionId);
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
		kafkaTemplate.send(TOPIC, NAME + ": postTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.postTweet(request);
		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": postTweet - end - " + transactionId);
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
		kafkaTemplate.send(TOPIC, NAME + ": updateTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.updateTweet(request, tweetId);
		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": updateTweet - end  - " + transactionId);
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
		kafkaTemplate.send(TOPIC, NAME + ": deleteTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			response = tweetService.deleteTweet(tweetId);
		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": deleteTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
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
		kafkaTemplate.send(TOPIC, NAME + ": likeTweet - start - " + transactionId);
		TweetResponse response = new TweetResponse();
		try {
			if (request.getLikeFlag().equalsIgnoreCase("Y")) {
				response = tweetService.likeTweet(request);
			} else {
				response = tweetService.unlikeTweet(request);
			}

		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME +" - " +  e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": likeTweet - end - " + transactionId);
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
		kafkaTemplate.send(TOPIC, NAME + ": replyTweet - start - " + transactionId);
		ReplyResponse response = new ReplyResponse();
		try {
			response = tweetService.replyTweet(request);

		} catch (Exception e) {
			kafkaTemplate.send(TOPIC, "Exception Occurred in " + NAME + " - " + e.getMessage() + " - " + transactionId);
		}

		kafkaTemplate.send(TOPIC, NAME + ": replyTweet - end - " + transactionId);
		response.getResponseHeader().getTransactionNotification().setTransactionId(transactionId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
