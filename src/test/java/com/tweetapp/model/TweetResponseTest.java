package com.tweetapp.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.response.TweetResponse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetResponseTest {

	
	@Test
	public void testTweetResponse() {
		
		TweetResponse r = new TweetResponse();
		TweetResponse rr = new TweetResponse(new ResponseHeader(), new ArrayList<Tweet>());
		
		rr.getData();
		rr.getResponseHeader();
		
		r.setData(new ArrayList<Tweet>());
		r.setResponseHeader(new ResponseHeader());
		System.out.println(r.toString());
	}
}
