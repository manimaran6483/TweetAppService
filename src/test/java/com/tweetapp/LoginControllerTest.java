package com.tweetapp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.controller.LoginController;
import com.tweetapp.controller.TweetController;
import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.Remarks;
import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;
import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.service.LoginService;
import com.tweetapp.util.TweetAppServiceUtil;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

	@InjectMocks
	private LoginController loginController;

	private MockMvc mockMvc;
	
	@Mock
	private LoginService loginService;

	private ObjectMapper mapper = new ObjectMapper();
	//@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		//MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	 }
	
	
	@Test
	public void testRegister() throws Exception{
		Mockito.when(loginService.registerUser(Mockito.any())).thenReturn(validUserResponse("200","SUCCESS"));
		 RegisterRequest request = new RegisterRequest(new RequestHeader(), "test", "test", "abc@email.com", "testt2343", "test", "test", "9999999999");
		 
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}
	

	@Test
	public void testRegisterException() throws Exception{
		Mockito.when(loginService.registerUser(Mockito.any())).thenThrow(new RuntimeException());
		RegisterRequest request = new RegisterRequest(new RequestHeader(), "test", "test", "abc@email.com", "testt2343", "test", "test", "9999999999");
		 
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String status = String.valueOf(result.getResponse().getStatus());
		
		assertEquals("200", status);
		
	}
	@Test
	public void testLogin() throws Exception{
		Mockito.when(loginService.loginUser(Mockito.any())).thenReturn(validUserResponse("200","SUCCESS"));
		 LoginRequest request = new LoginRequest(new RequestHeader(),"test","test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
	}
	

	@Test
	public void testLoginException() throws Exception{
		Mockito.when(loginService.loginUser(Mockito.any())).thenThrow(new RuntimeException());
		 LoginRequest request = new LoginRequest(new RequestHeader(),"test","test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
		
	}
	
	@Test
	public void testForgotPassword() throws Exception{
		Mockito.when(loginService.forgotPassword(Mockito.any(),Mockito.any())).thenReturn(validUserResponse("200","SUCCESS"));
		ForgotPasswordRequest request = new ForgotPasswordRequest(new RequestHeader(), "test", "test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/forgot","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
	}
	

	@Test
	public void testForgotPasswordException() throws Exception{
		Mockito.when(loginService.forgotPassword(Mockito.any(),Mockito.any())).thenThrow(new RuntimeException());
		ForgotPasswordRequest request = new ForgotPasswordRequest(new RequestHeader(), "test", "test");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/{username}/forgot","test").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
		
	}
	
	@Test
	public void getAllUsers() throws Exception{
		Mockito.when(loginService.getAllUsers()).thenReturn(validUserResponse("200","SUCCESS"));
		MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/users/all").header("transactionId", "test").accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
	}
	

	@Test
	public void getAllUsersException() throws Exception{
		Mockito.when(loginService.getAllUsers()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/all").header("transactionId", "test").accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
		
	}
	
	@Test
	public void searchUser() throws Exception{
		Mockito.when(loginService.searchByUserName(Mockito.any())).thenReturn(validUserResponse("200","SUCCESS"));
		MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/user/search/{username}","test").header("transactionId", "test").accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
	}
	

	@Test
	public void searchUserException() throws Exception{
		Mockito.when(loginService.searchByUserName(Mockito.any())).thenThrow(new RuntimeException());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/search/{username}","test").header("transactionId", "test").accept(MediaType.APPLICATION_JSON)).andReturn();
		String status = String.valueOf(result.getResponse().getStatus());
		assertEquals("200", status);
		
	}
	
	private UserResponse validUserResponse(String code, String status) {
		UserResponse response = new UserResponse();
		response.setResponseHeader(new ResponseHeader());
		response.getResponseHeader().setTransactionNotification(new TransactionNotification());
		response.getResponseHeader().getTransactionNotification().setRemarks(new Remarks());
		TweetAppServiceUtil.populateResponseHeader(response.getResponseHeader(), code, status, new ArrayList<Message>());
		
		return response;
	}
	
}
