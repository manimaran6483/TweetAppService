package com.tweetapp.model.common.composite;

import java.util.ArrayList;
import java.util.List;

import com.tweetapp.model.common.atomic.Message;

public class Remarks {

	private List<Message> messages = new ArrayList<>();

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
}
