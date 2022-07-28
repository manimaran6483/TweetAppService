package com.tweetapp;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.controller.TweetController;
import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.Remarks;
import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;
import com.tweetapp.model.request.LikeRequest;
import com.tweetapp.model.request.ReplyRequest;
import com.tweetapp.model.request.TweetRequest;
import com.tweetapp.model.response.ReplyResponse;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.service.TweetService;
import com.tweetapp.util.TweetAppServiceUtil;

import io.micrometer.core.ipc.http.HttpSender.Response;


@ExtendWith(MockitoExtension.class)
public class TweetControllerTest {

	@InjectMocks
	private TweetController tweetController;

	private MockMvc mockMvc;
	
	@Mock
	private TweetService tweetService;

	private ObjectMapper mapper = new ObjectMapper();
	//@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		//MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
	 }
	
	@Test
	public void testAllTweets() throws Exception{
		Mockito.when(tweetService.getAllTweets()).thenReturn(new TweetResponse());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/all").header("transactionId", "id").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}
	
	@Test
	public void testAllTweetsException() throws Exception{
		Mockito.when(tweetService.getAllTweets()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/all").header("transactionId", "id").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}
	
	@Test
	public void getusertweets() throws Exception{
		Mockito.when(tweetService.getTweetsForUser(Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/test").header("transactionId", "id").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}
	
	@Test
	public void getusertweetsException() throws Exception{
		Mockito.when(tweetService.getTweetsForUser(Mockito.any())).thenThrow(new RuntimeException());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/test").header("transactionId", "id").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}

	@Test
	public void testPostTweet() throws Exception {
		Mockito.when(tweetService.postTweet(Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/add","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201", status);
	}
	
	@Test
	public void testPostTweetException() throws Exception {
		Mockito.when(tweetService.postTweet(Mockito.any())).thenThrow(new RuntimeException());
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/add","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201", status);
	}
	
	@Test
	public void testUpdateTWeet() throws Exception {
		Mockito.when(tweetService.updateTweet(Mockito.any(),Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/{username}/update/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
	}
	@Test
	public void testUpdateTWeetException() throws Exception {
		Mockito.when(tweetService.updateTweet(Mockito.any(),Mockito.any())).thenThrow(new RuntimeException());
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/{username}/update/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
	}
	
	@Test
	public void testDeleteTweet() throws Exception {
		Mockito.when(tweetService.deleteTweet(Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/{username}/delete/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("204", status);
	}
	
	@Test
	public void testDeleteTweetException() throws Exception {
		Mockito.when(tweetService.deleteTweet(Mockito.any())).thenThrow(new RuntimeException());
		TweetRequest request = new TweetRequest(new RequestHeader(),"test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/{username}/delete/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("204", status);
	}
	@Test
	public void testLikeTweet() throws Exception{
		Mockito.when(tweetService.likeTweet(Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		LikeRequest request = new LikeRequest(new RequestHeader(),"Y","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/like/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
	}
	@Test
	public void testLikeTweetException() throws Exception{
		Mockito.when(tweetService.likeTweet(Mockito.any())).thenThrow(new RuntimeException());
		LikeRequest request = new LikeRequest(new RequestHeader(),"Y","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/like/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
	}
	@Test
	public void testUnlikeTweet() throws Exception{
		Mockito.when(tweetService.unlikeTweet(Mockito.any())).thenReturn(getvalidResponse("0","SUCCESS"));
		LikeRequest request = new LikeRequest(new RequestHeader(),"N","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/like/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
	}
	@Test
	public void testUnlikeTweetException() throws Exception{
		Mockito.when(tweetService.unlikeTweet(Mockito.any())).thenThrow(new RuntimeException());
		LikeRequest request = new LikeRequest(new RequestHeader(),"N","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/like/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
	}
	
	@Test
	public void testReplyTweet() throws Exception {
		Mockito.when(tweetService.replyTweet(Mockito.any())).thenReturn(getvalidReplyResponse("0","SUCCESS"));
		ReplyRequest request = new ReplyRequest(new RequestHeader(),"N","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/reply/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
		
	}
	
	@Test
	public void testReplyTweetException() throws Exception {
		Mockito.when(tweetService.replyTweet(Mockito.any())).thenThrow(new RuntimeException());
		ReplyRequest request = new ReplyRequest(new RequestHeader(),"N","test","test","test");
		request.getRequestHeader().setTransactionId("test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/reply/{id}","test","test").contentType(MediaType.APPLICATION_JSON)
				.header("transactionId", "id").content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("201",status);
		
	}
	
	private ReplyResponse getvalidReplyResponse(String code, String status) {
		ReplyResponse response = new ReplyResponse();
		response.setResponseHeader(new ResponseHeader());
		response.getResponseHeader().setTransactionNotification(new TransactionNotification());
		response.getResponseHeader().getTransactionNotification().setRemarks(new Remarks());
		TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), code, status, new ArrayList<Message>());
		
		return response;
	}

	public TweetResponse getvalidResponse(String code,String status) {
		TweetResponse response = new TweetResponse();
		response.setResponseHeader(new ResponseHeader());
		response.getResponseHeader().setTransactionNotification(new TransactionNotification());
		response.getResponseHeader().getTransactionNotification().setRemarks(new Remarks());
		TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), code, status, new ArrayList<Message>());
		
		return response;
	}
}