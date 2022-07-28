package com.tweetapp.model.common.composite;


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
public class ResponseHeader {

	private TransactionNotification transactionNotification = new TransactionNotification();

}
