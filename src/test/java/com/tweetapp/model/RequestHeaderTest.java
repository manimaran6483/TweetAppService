package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.atomic.Consumer;
import com.tweetapp.model.common.composite.RequestHeader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RequestHeaderTest{

	
	@Test
	public void testRequestHeader() {
		
		RequestHeader r= new RequestHeader();
		RequestHeader rr = new RequestHeader(new Consumer(), "test");
		
		rr.getConsumer();
		rr.getTransactionId();
		
		r.setConsumer(new Consumer());
		r.setTransactionId("test");
		
		System.out.println(r.toString());
	}
}
