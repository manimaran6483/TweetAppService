package com.tweetapp.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DynamoDBTable(tableName = "tweet")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

	@Id
	@DynamoDBHashKey(attributeName = "id")
	private String id;
	
	@DynamoDBAttribute(attributeName = "tweet")
	private String tweet;
	
	@DynamoDBAttribute(attributeName = "tag")
	private String tag;
	
	@DynamoDBAttribute(attributeName = "postedDate")
	private String postedDate;
	
	@DynamoDBAttribute(attributeName = "updateDate")
	private String updateDate;
	
	@DynamoDBAttribute(attributeName = "userId")
	private String userId;
	
	@DynamoDBAttribute(attributeName = "likeCount")
	private String likeCount;
	
	@DynamoDBAttribute(attributeName = "replyCount")
	private String replyCount;

	@DynamoDBAttribute(attributeName = "loginId")
	private String loginId;
}
