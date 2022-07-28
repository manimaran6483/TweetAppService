package com.tweetapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String userId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String loginId;

	private String contactNumber;

	private String registeredDate;
}
