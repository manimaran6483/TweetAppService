package com.tweetapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.entity.Reply;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.entity.TweetLike;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.response.ReplyResponse;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.repository.TweetLikeRepository;
import com.tweetapp.repository.TweetReplyRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetService;
import com.tweetapp.util.TweetAppServiceUtil;

@Service
public class TweetServiceImpl implements TweetService {

	private static final Logger LOGGER = LogManager.getLogger(TweetServiceImpl.class);

	private static final String NAME = "TweetServiceImpl";

	@Autowired
	private TweetRepository tweetRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TweetLikeRepository tweetLikeRepo;
	
	@Autowired
	private TweetReplyRepository replyRepo;

	@Override
	public TweetResponse getAllTweets() {

		LOGGER.debug(NAME + " getAllTweets start");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		List<Tweet> tweetList = tweetRepo.findAll();

		if (tweetList != null && !tweetList.isEmpty()) {
			tweetList = tweetList.stream().sorted((a, b) -> b.getPostedDate().compareTo(a.getPostedDate()))
					.collect(Collectors.toList());
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			response.setData(tweetList);
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " getAllTweets end");
		return response;
	}

	@Override
	public TweetResponse getTweetsForUser(String username) {
		LOGGER.debug(NAME + " getTweetsForUser start");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		User user = userRepo.findByLoginId(username);
		if (user != null) {
			List<Tweet> tweetList = tweetRepo.findAllByUserId(user.getUserId());
			tweetList = tweetList.stream().sorted((a, b) -> b.getPostedDate().compareTo(a.getPostedDate()))
					.collect(Collectors.toList());
			if (tweetList != null && !tweetList.isEmpty()) {
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
				response.setData(tweetList);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
						TweetAppConstants.NO_DATA_FOUND_MSG, TweetAppConstants.NO_DATA_FOUND_MSG);
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid username in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " getTweetsForUser end");
		return response;
	}

	@Override
	public TweetResponse postTweet(TweetRequest request) {
		LOGGER.debug(NAME + " postTweet start ");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		Tweet tweet = TweetAppServiceUtil.postTweet(request);
		Optional<User> user = userRepo.findById(request.getUserId());
		if (user.isPresent()) {
			Tweet twt = tweetRepo.save(tweet);
			List<Tweet> tweetList = new ArrayList<>();
			if (twt != null) {
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
				tweetList.add(twt);
				response.setData(tweetList);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Post Creation Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid user id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " postTweet end");
		return response;
	}

	@Override
	public TweetResponse updateTweet(TweetRequest request, String tweetId) {
		LOGGER.debug(NAME + " updateTweet start ");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		Optional<Tweet> tweetById = tweetRepo.findById(tweetId);
		if (tweetById.isPresent()) {
			Tweet tweet = TweetAppServiceUtil.updateTweet(request, tweetById.get());
			Tweet twt = tweetRepo.save(tweet);
			List<Tweet> tweetList = new ArrayList<>();
			if (twt != null) {
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
				tweetList.add(twt);
				response.setData(tweetList);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Post Update Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid tweet id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " updateTweet end");
		return response;
	}

	@Override
	public TweetResponse deleteTweet(String tweetId) {
		LOGGER.debug(NAME + " updateTweet start ");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		Optional<Tweet> tweetById = tweetRepo.findById(tweetId);
		if (tweetById.isPresent()) {
			tweetRepo.deleteById(tweetId);
			TweetAppServiceUtil.populateMessages(messages, "204", "Deleted", "Post deleted");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);

		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid tweet id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " updateTweet end");
		return response;
	}

	@Override
	public TweetResponse likeTweet(LikeRequest request) {
		LOGGER.debug(NAME + " likeTweet start ");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		Optional<Tweet> tweetById = tweetRepo.findById(request.getTweetId());
		if (tweetById.isPresent()) {
			TweetLike tweetLike = TweetAppServiceUtil.createTweetLike(request);
			TweetLike twt = tweetLikeRepo.save(tweetLike);
			if (twt != null) {
				Tweet tweet = TweetAppServiceUtil.incrementLikeCount(tweetById.get());
				Tweet tw = tweetRepo.save(tweet);
				TweetAppServiceUtil.populateMessages(messages, "200", "Success", "Tweet Like Updated - "+ twt.getId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Like Update Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid tweet id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " likeTweet end");
		return response;

	}

	

	@Override
	public TweetResponse unlikeTweet(@Valid LikeRequest request) {
		LOGGER.debug(NAME +" unlikeTweet start ");
		TweetResponse response = new TweetResponse();
		List<Message> messages = new ArrayList<>();
		Optional<Tweet> tweetById = tweetRepo.findById(request.getTweetId());
		Optional<TweetLike> tweetLikeById = tweetLikeRepo.findById(request.getLikeId());
		if(tweetLikeById.isPresent()) {
			tweetLikeRepo.deleteById(request.getLikeId());
			Tweet tweet = TweetAppServiceUtil.decrementLikeCount(tweetById.get());
			Tweet tw = tweetRepo.save(tweet);
			if(tw!=null) {
				TweetAppServiceUtil.populateMessages(messages,"200","Success","Tweet Like removed - "+request.getLikeId());
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"0","SUCCESS",messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Like Update Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
			
		}else {
			TweetAppServiceUtil.populateMessages(messages,TweetAppConstants.NO_DATA_FOUND_CODE,TweetAppConstants.NO_DATA_FOUND_MSG,"Invalid like id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(),"1","FAILURE",messages);
		}
		
		LOGGER.debug(NAME +" unlikeTweet end");
		return response;
	}

	@Override
	public ReplyResponse replyTweet(ReplyRequest request) {
		LOGGER.debug(NAME + " likeTweet start ");
		ReplyResponse response = new ReplyResponse();
		List<Message> messages = new ArrayList<>();
		Optional<Tweet> tweetById = tweetRepo.findById(request.getTweetId());
		if (tweetById.isPresent()) {
			Reply reply = TweetAppServiceUtil.createReply(request);
			Reply rply = replyRepo.save(reply);
			if (rply != null) {
				Tweet tweet = TweetAppServiceUtil.incrementReplyCount(tweetById.get());
				tweetRepo.save(tweet);
				TweetAppServiceUtil.populateMessages(messages, "200", "Success", "Reply created");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "0", "SUCCESS", messages);
			} else {
				TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.INTERNAL_SERVER_ERROR_CODE,
						TweetAppConstants.INTERNAL_SERVER_ERROR_MSG, "Reply creation Failed");
				TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
			}
		} else {
			TweetAppServiceUtil.populateMessages(messages, TweetAppConstants.NO_DATA_FOUND_CODE,
					TweetAppConstants.NO_DATA_FOUND_MSG, "Invalid tweet id in request");
			TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), "1", "FAILURE", messages);
		}

		LOGGER.debug(NAME + " likeTweet end");
		return response;	}

}
