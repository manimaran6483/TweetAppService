package com.tweetapp.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;
import com.tweetapp.model.entity.Reply;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.entity.TweetLike;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.request.UserDTO;

@Service
public class TweetAppServiceUtil {

	public static void populateResponseHeader(ResponseHeader responseHeader,String code,String status,List<Message> messages) {
		
		responseHeader.setTransactionNotification(new TransactionNotification());
		responseHeader.getTransactionNotification().setStatus(status);
		responseHeader.getTransactionNotification().setStatusCode(code);
		responseHeader.getTransactionNotification().getRemarks().setMessages(messages);
		responseHeader.getTransactionNotification().setResponseDateTime(getCurrentDate());
	}
	
	
	public static String generateRandomUserId() {
		return String.format("%07d", RandomUtils.nextInt(0, 10000000));
	}
	public static String generateRandomTweetId() {
		return String.format("%08d", RandomUtils.nextInt(0, 100000000));
	}
	public static String generateRandomLikeId() {
		return String.format("%10d", RandomUtils.nextLong(0, 10000000000L));
	}
	
	public static String generateRandomReplyId() {
		return String.format("%9d", RandomUtils.nextInt(0, 1000000000));
	}
	
	public static String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public static User registerUser(RegisterRequest request) {
		
		User user = new User();
		user.setUserId(generateRandomUserId());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailId(request.getEmailId());
		user.setPassword(encodePassword(request.getPassword()));
		user.setLoginId(request.getLoginId());
		user.setContactNumber(request.getContactNumber());
		user.setRegisteredDate(getCurrentDate());
		return user;
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static void populateMessages(List<Message> messages,String code,String message,String description) {
		Message msg = new Message();
		msg.setCode(code);
		msg.setDescription(description);
		msg.setMessage(message);
		messages.add(msg); 
	}

	public static boolean validatePassword(String password, String password2) {
		if(new BCryptPasswordEncoder().matches(password, password2)) {
			return true;
		}
		return false;
		
	}

	public static List<UserDTO> userDtoMapper(List<User> userList) {
		List<UserDTO> userDTOList = new ArrayList<>();
		for(User u : userList) {
			UserDTO user = new UserDTO();
			user.setContactNumber(u.getContactNumber());
			user.setEmailId(u.getEmailId());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setLoginId(u.getLoginId());
			user.setRegisteredDate(u.getRegisteredDate());
			user.setUserId(u.getUserId());
			
			userDTOList.add(user);
		}
		return userDTOList;
	}


	public static Tweet postTweet(TweetRequest request) {
		Tweet tweet = new Tweet(generateRandomTweetId(),request.getTweet(),request.getTag(),getCurrentDate(),getCurrentDate(),
				request.getUserId(),"0","0");
		return tweet;
	}


	public static Tweet updateTweet(TweetRequest request, Tweet tweetById) {
		tweetById.setTag(request.getTag());
		tweetById.setTweet(request.getTweet());
		tweetById.setUpdateDate(getCurrentDate());
		return tweetById;
	}


	public static TweetLike createTweetLike(LikeRequest request) {
		TweetLike like = new TweetLike();
		like.setId(generateRandomLikeId());
		like.setLikeUserName(request.getUserName());
		like.setTweetId(request.getTweetId());
		
		return like;
	}


	public static Tweet incrementLikeCount(Tweet tweet) {
		int likeCount = Integer.parseInt(tweet.getLikeCount());
		tweet.setLikeCount(String.valueOf(++likeCount));
		return tweet;
	}


	public static Reply createReply(ReplyRequest request) {
		Reply reply = new Reply();
		
		reply.setId(generateRandomReplyId());
		reply.setComment(request.getComment());
		reply.setTag(request.getTag());
		reply.setTweetId(request.getTweetId());
		reply.setUsername(request.getUserName());
		return reply;
	}


	public static Tweet incrementReplyCount(Tweet tweet) {
		int replyCount = Integer.parseInt(tweet.getReplyCount());
		tweet.setLikeCount(String.valueOf(++replyCount));
		return tweet;
	}


	public static Tweet decrementLikeCount(Tweet tweet) {
		int likeCount = Integer.parseInt(tweet.getLikeCount());
		if(likeCount!=0 && likeCount>0) {
			tweet.setLikeCount(String.valueOf(--likeCount));
		}
		return tweet;
	}
}

