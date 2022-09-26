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

@DynamoDBTable(tableName = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@DynamoDBHashKey(attributeName = "userId")
	private String userId;
	
	@DynamoDBAttribute(attributeName = "firstName")
	private String firstName;
	
	@DynamoDBAttribute(attributeName = "lastName")
	private String lastName;
	
	@DynamoDBAttribute(attributeName = "emailId")
	private String emailId;

	@DynamoDBAttribute(attributeName = "loginId")
	private String loginId;

	@DynamoDBAttribute(attributeName = "password")
	private String password;

	@DynamoDBAttribute(attributeName = "contactNumber")
	private String contactNumber;
	
	@DynamoDBAttribute(attributeName = "registeredDate")
	private String registeredDate;


}
