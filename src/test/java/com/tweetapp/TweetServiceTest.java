package com.tweetapp;


import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.entity.Reply;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.entity.TweetLike;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.response.ReplyResponse;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.repository.TweetLikeRepository;
import com.tweetapp.repository.TweetReplyRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.impl.TweetServiceImpl;
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TweetServiceTest {

	
	@InjectMocks
	private TweetServiceImpl tweetService;
	
	@Mock
	private TweetRepository tweetRepo;
	
	@Mock
	private TweetLikeRepository tweetLikeRepo;
	
	@Mock
	private UserRepository userRepo; 
	
	@Mock
	private TweetReplyRepository replyRepo;
	
	@Test
	public void testGetAllTweetsSuccess() {
		
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet("12","test","test","27-07-2022 01:24:33","test","test","test","test"));
		tweetList.add(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","test","test"));
		Mockito.when(tweetRepo.findAll()).thenReturn(tweetList);
		
		TweetResponse response = tweetService.getAllTweets();
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	@Test
	public void testGetAllTweetsFail() {
		Mockito.when(tweetRepo.findAll()).thenReturn(null);
		
		TweetResponse response = tweetService.getAllTweets();
		
		Assert.assertEquals("FAILURE",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test 
	public void getTweetsForUserSuccess() {
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet("12","test","test","27-07-2022 01:24:33","test","test","test","test"));
		tweetList.add(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","test","test"));
		Mockito.when(userRepo.findByLoginId(Mockito.any())).thenReturn(new User());
		Mockito.when(tweetRepo.findAllByUserId(Mockito.any())).thenReturn(tweetList);
		TweetResponse response = tweetService.getTweetsForUser("user");
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	@Test 
	public void getTweetsForUserNoUser() {
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet());
		Mockito.when(userRepo.findByLoginId(Mockito.any())).thenReturn(null);
		Mockito.when(tweetRepo.findAllByUserId(Mockito.anyString())).thenReturn(tweetList);
		TweetResponse response = tweetService.getTweetsForUser("user");
		
		Assert.assertEquals("FAILURE",response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	@Test 
	public void getTweetsForUserNoTweet() {
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet());
		Mockito.when(userRepo.findByLoginId(Mockito.any())).thenReturn(new User());
		Mockito.when(tweetRepo.findAllByUserId(Mockito.anyString())).thenReturn(null);
		TweetResponse response = tweetService.getTweetsForUser("user");
		
		Assert.assertEquals("FAILURE",response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	
	@Test 
	public void postTweetSuccess() {
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet("12","test","test","27-07-2022 01:24:33","test","test","test","test"));
		tweetList.add(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","test","test"));
		Optional<User> opt = Optional.ofNullable(new User());
		Mockito.when(userRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.postTweet(new TweetRequest());
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	@Test 
	public void postTweetInternalServerError() {
		Optional<User> opt = Optional.ofNullable(new User());
		Mockito.when(userRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(null);
		TweetResponse response = tweetService.postTweet(new TweetRequest());
		
		Assert.assertEquals("Post Creation Failed",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	@Test 
	public void postTweetSuccessNODataFound() {
		Optional<User> opt = Optional.ofNullable(null);
		Mockito.when(userRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.postTweet(new TweetRequest());
		
		Assert.assertEquals("Invalid user id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	@Test 
	public void updateTweetSuccess() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet());
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.updateTweet(new TweetRequest(),"test");
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	@Test 
	public void updateTweetInternalServerError() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet());
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(null);
		TweetResponse response = tweetService.updateTweet(new TweetRequest(),"test");
		
		Assert.assertEquals("Post Update Failed",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	@Test 
	public void updateTweetNoDAtaFound() {
		Optional<Tweet> opt = Optional.ofNullable(null);
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.updateTweet(new TweetRequest(),"test");
		
		Assert.assertEquals("Invalid tweet id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	
	@Test 
	public void deleteTweetSuccess() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet());
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		doNothing().when(tweetRepo).deleteById(Mockito.any());
		TweetResponse response = tweetService.deleteTweet("test");
		
		Assert.assertEquals("Post deleted",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	
	@Test 
	public void deleteTweetFail() {
		Optional<Tweet> opt = Optional.ofNullable(null);
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		doNothing().when(tweetRepo).deleteById(Mockito.any());
		TweetResponse response = tweetService.deleteTweet("test");
		
		Assert.assertEquals("Invalid tweet id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	
	@Test
	public void likeTweetSuccess() {
		List<Tweet> tweetList= new ArrayList<>();
		tweetList.add(new Tweet("12","test","test","27-07-2022 01:24:33","test","test","test","test"));
		tweetList.add(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","test","test"));
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","0","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetLikeRepo.save(Mockito.any())).thenReturn(new TweetLike());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.likeTweet(new LikeRequest());
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void likeTweetError() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","0","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetLikeRepo.save(Mockito.any())).thenReturn(null);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.likeTweet(new LikeRequest());
		
		Assert.assertEquals("Like Update Failed",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void likeTweetNoData() {
		Optional<Tweet> opt = Optional.ofNullable(null);
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetLikeRepo.save(Mockito.any())).thenReturn(new TweetLike());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.likeTweet(new LikeRequest());
		
		Assert.assertEquals("Invalid tweet id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void unlikeTweetSuccess() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","1","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetLikeRepo.findById(Mockito.any())).thenReturn(Optional.ofNullable(new TweetLike("test","test","test")));
		doNothing().when(tweetLikeRepo).deleteById(Mockito.any());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.unlikeTweet(new LikeRequest());
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void unlikeTweetError() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","1","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(tweetLikeRepo.findById(Mockito.any())).thenReturn(Optional.ofNullable(new TweetLike("test","test","test")));
		doNothing().when(tweetLikeRepo).deleteById(Mockito.any());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(null);
		TweetResponse response = tweetService.unlikeTweet(new LikeRequest());
		
		Assert.assertEquals("Like Update Failed",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void unlikeTweetNoData() {
		Optional<Tweet> opt = Optional.ofNullable(null);
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		doNothing().when(tweetLikeRepo).deleteById(Mockito.any());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		TweetResponse response = tweetService.unlikeTweet(new LikeRequest());
		
		Assert.assertEquals("Invalid like id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void replyTweetSuccess() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","1","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(replyRepo.save(Mockito.any())).thenReturn(new Reply());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		ReplyResponse response = tweetService.replyTweet(new ReplyRequest());
		
		Assert.assertEquals("Reply created",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void replyTweetError() {
		Optional<Tweet> opt = Optional.ofNullable(new Tweet("12","test","test","28-07-2022 01:24:33","test","test","1","0"));
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(replyRepo.save(Mockito.any())).thenReturn(null);
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		ReplyResponse response = tweetService.replyTweet(new ReplyRequest());
		
		Assert.assertEquals("Reply creation Failed",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void replyTweetNoData() {
		Optional<Tweet> opt = Optional.ofNullable(null);
		Mockito.when(tweetRepo.findById(Mockito.any())).thenReturn(opt);
		Mockito.when(replyRepo.save(Mockito.any())).thenReturn(new Reply());
		Mockito.when(tweetRepo.save(Mockito.any())).thenReturn(new Tweet());
		ReplyResponse response = tweetService.replyTweet(new ReplyRequest());
		
		Assert.assertEquals("Invalid tweet id in request",response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
}
