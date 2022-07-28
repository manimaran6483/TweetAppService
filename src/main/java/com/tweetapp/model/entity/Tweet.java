package com.tweetapp.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "Tweet")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

	@Id
	private String id;
	
	private String tweet;
	
	private String tag;
	
	private String postedDate;
	
	private String updateDate;
	
	private String userId;
	
	private String likeCount;
	
	private String replyCount;

	
}
