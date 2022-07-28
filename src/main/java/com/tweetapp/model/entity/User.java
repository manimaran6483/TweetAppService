package com.tweetapp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "UserDetails")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;

	private String loginId;

	private String password;

	private String contactNumber;
	
	private String registeredDate;


}
