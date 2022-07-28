package com.tweetapp.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "TweetReply")
@Setter
@Getter
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
