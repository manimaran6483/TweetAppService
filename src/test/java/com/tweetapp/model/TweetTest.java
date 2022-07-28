package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.entity.Tweet;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetTest {

	
	@Test
	public void testTweet() {
		
		Tweet t = new Tweet();
		Tweet tt = new Tweet("test", "test", "test", "test", "ttest", "test", "test", "test");
		tt.getId();
		tt.getLikeCount();
		tt.getReplyCount();
		tt.getPostedDate();
		tt.getTag();
		tt.getTweet();
		tt.getUpdateDate();
		tt.getUserId();
		
		t.setId("test");
		t.setLikeCount("test");
		t.setPostedDate("test");
		t.setReplyCount("test");
		t.setTag("test");
		t.setTweet("test");
		t.setUpdateDate("test");
		t.setUserId("test");
		
		System.out.println(t.toString());
	}
}
