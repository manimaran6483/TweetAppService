package com.tweetapp.model.common.composite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionNotification {

	private String status;
	private String statusCode;
	private String responseDateTime;
	private String transactionId;
	private Remarks remarks = new Remarks();
}
