package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ResponseHeaderTest {

	@Test
	public void testResponseHeader() {
		ResponseHeader r = new ResponseHeader();
		ResponseHeader rr = new ResponseHeader(new TransactionNotification());
		
		rr.getTransactionNotification();
		r.setTransactionNotification(new TransactionNotification());
		
		System.out.println(r.toString());
	}
}
