package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.entity.Reply;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplyTest {

	
	@Test
	public void testReply() {
		
		Reply r = new Reply();
		Reply rr = new Reply("etst", "test", "test", "test", "test");
		
		rr.getComment();
		rr.getId();
		rr.getTag();
		rr.getTweetId();
		rr.getUsername();
		
		r.setComment("test");
		r.setId("test");
		r.setTag("test");
		r.setTweetId("test");
		r.setUsername("test");
		
		System.out.println(r.toString());
	}
}
