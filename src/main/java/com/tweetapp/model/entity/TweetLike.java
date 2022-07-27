package com.tweetapp.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "TweetLike")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TweetLike {

	
	private String id;
	private String likeUserName;
	private String tweetId;
}
