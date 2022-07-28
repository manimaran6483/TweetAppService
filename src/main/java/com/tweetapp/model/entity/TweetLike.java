package com.tweetapp.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "TweetLike")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TweetLike {

	
	private String id;
	private String likeUserName;
	private String tweetId;
}
