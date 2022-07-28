package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.entity.TweetLike;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetLikeTest {

	
	@Test
	public void testTweetLike() {
		TweetLike r = new TweetLike();
		TweetLike rr = new TweetLike("id", "test", "test");
		
		rr.getId();
		rr.getLikeUserName();
		rr.getTweetId();
		r.setId("test");
		r.setLikeUserName("test");
		r.setTweetId("test");
		
		System.out.println(r.toString());
	}
}
