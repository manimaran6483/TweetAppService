package com.tweetapp.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "TweetReply")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	@Id
	private String id;
	
	private String comment;
	
	private String tag;
	
	private String tweetId;
	
	private String username;

}
