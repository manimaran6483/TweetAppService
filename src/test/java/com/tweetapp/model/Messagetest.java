package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.atomic.Message;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Messagetest {

	
	@Test
	public void testMessage() {
		Message m = new Message();
		Message mm = new Message("test","test","test");
		
		mm.getCode();
		mm.getDescription();
		mm.getMessage();
		
		m.setCode("test");
		m.setDescription("test");
		m.setMessage("test");
		
		System.out.println(m.toString());
	}
}
