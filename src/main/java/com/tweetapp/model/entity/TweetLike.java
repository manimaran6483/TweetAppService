package com.tweetapp.model.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DynamoDBTable(tableName = "tweetlike")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TweetLike {

	@DynamoDBHashKey(attributeName = "id")
	private String id;
	
	@DynamoDBAttribute(attributeName = "likeUserName")
	private String likeUserName;
	
	@DynamoDBAttribute(attributeName = "tweetId")
	private String tweetId;
	
}
