package com.tweetapp.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.RegisterRequest;

@Service
public class TweetAppServiceUtil {

	public static void populateResponseHeader(ResponseHeader responseHeader,String code,String status,List<Message> messages) {
		
		responseHeader.setTransactionNotification(new TransactionNotification());
		responseHeader.getTransactionNotification().setStatus(status);
		responseHeader.getTransactionNotification().setStatusCode(code);
		responseHeader.getTransactionNotification().getRemarks().setMessages(messages);
		responseHeader.getTransactionNotification().setResponseDateTime(new Date());
	}
	
	public static String generateRandomUserId() {
		return String.format("%07d", RandomUtils.nextInt(0, 10000000));
	}
	
	public static User registerUser(RegisterRequest request) {
		User user = new User();
		user.setUserId(generateRandomUserId());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailId(request.getEmailId());
		user.setPassword(request.getPassword());
		user.setLoginId(request.getLoginId());
		user.setContactNumber(request.getContactNumber());
		
		return user;
	}

	public static void populateMessages(List<Message> messages,String code,String message,String description) {
		Message msg = new Message();
		msg.setCode(code);
		msg.setDescription(description);
		msg.setMessage(message);
		messages.add(msg); 
	}
}

