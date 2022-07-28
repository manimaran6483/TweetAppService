package com.tweetapp.model.common.atomic;


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
public class Consumer {

	private String id;
	private String name;
	private String businessTransactionType;
	private String type;
	private String requestDateTime;
	private String hostName;
}
