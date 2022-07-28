package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.ReplyRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplyRequestTest {

	@Test
	public void testReplyRequest() {
		
		ReplyRequest r = new ReplyRequest();
		ReplyRequest rr = new ReplyRequest(new RequestHeader(), "test", "test", "test", "test");
		
		rr.getComment();
		rr.getRequestHeader();
		rr.getTag();
		rr.getTweetId();
		rr.getUserName();
		
		r.setComment("test");
		r.setRequestHeader(new RequestHeader());
		r.setTag("tst");
		r.setTweetId("test");
		r.setUserName("test");
		System.out.println(r.toString());
	}
	
}
