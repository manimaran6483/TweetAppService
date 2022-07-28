package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.LikeRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LikeRequestTest {

	@Test
	public void testLikeRequest() {
		LikeRequest r = new LikeRequest();
		LikeRequest rr = new LikeRequest(new RequestHeader(), "test", "test", "test", "test");
		
		rr.getLikeFlag();
		rr.getLikeId();
		rr.getTweetId();
		rr.getUserName();
		rr.getRequestHeader();
		
		r.setLikeFlag("test");
		r.setLikeId("test");
		r.setTweetId("test");
		r.setUserName("test");
		r.setRequestHeader(new RequestHeader());
		System.out.println(r.toString());
	}
			
			
}
