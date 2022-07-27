package com.tweetapp.model.common.composite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHeader {

	private TransactionNotification transactionNotification = new TransactionNotification();

}
