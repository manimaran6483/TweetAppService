package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.TweetRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetRequestTest {

	
	@Test
	public void testTweetRequest() {
		
		TweetRequest r = new TweetRequest();
		TweetRequest rr = new TweetRequest(new RequestHeader(), "test", "test", "test");
		rr.getRequestHeader();
		rr.getTag();
		rr.getTweet();
		rr.getUserId();
		
		r.setRequestHeader(new RequestHeader());
		r.setTag("tst");
		r.setTweet("tst");
		r.setUserId("user");
		
		System.out.println(r.toString());
	}
}
