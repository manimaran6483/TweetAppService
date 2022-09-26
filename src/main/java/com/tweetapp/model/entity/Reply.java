package com.tweetapp.model.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DynamoDBTable(tableName = "reply")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	@DynamoDBHashKey(attributeName = "id")
	private String id;
	
	@DynamoDBAttribute(attributeName = "comment")
	private String comment;
	
	@DynamoDBAttribute(attributeName = "tag")
	private String tag;
	
	@DynamoDBAttribute(attributeName = "tweetId")
	private String tweetId;
	
	@DynamoDBAttribute(attributeName = "username")
	private String username;

}
