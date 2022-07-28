package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.atomic.Consumer;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsumerTest {

	
	@Test
	public void testconsumer() {
		
		Consumer c = new Consumer();
		Consumer cc = new Consumer("test", "test", "test", "test", "test", "test");
		
		cc.getBusinessTransactionType();
		cc.getHostName();
		cc.getType();
		cc.getId();
		cc.getName();
		cc.getRequestDateTime();
		
		c.setBusinessTransactionType("test");
		c.setHostName("test");
		c.setType("test");
		c.setId("test");
		c.setRequestDateTime("test");
		c.setName("test");
		
		System.out.println(c.toString());
	}
}
