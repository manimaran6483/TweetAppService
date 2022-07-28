package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.Remarks;
import com.tweetapp.model.common.composite.TransactionNotification;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionNotificationTest {

	
	@Test 
	public void testTransactionNotification() {
		TransactionNotification t = new TransactionNotification();
		TransactionNotification tt = new TransactionNotification("test", "test", "test", "test", new Remarks());
		
		tt.getRemarks();
		tt.getResponseDateTime();
		tt.getStatus();
		tt.getStatusCode();
		tt.getTransactionId();
		
		t.setRemarks(new Remarks());
		t.setResponseDateTime("test");
		t.setStatus("test");
		t.setStatusCode("test");
		t.setTransactionId("test");
		
		System.out.println(t.toString());
	}
}
