package com.tweetapp;

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

import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.Remarks;
import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.common.composite.TransactionNotification;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.entity.User;
import com.tweetapp.model.request.ForgotPasswordRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.RegisterRequest;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.impl.LoginServiceImpl;
import com.tweetapp.util.TweetAppServiceUtil;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceTest {

	@InjectMocks
	private LoginServiceImpl loginService;
	
	@Mock
	private UserRepository userRepo;
	
	
	@Test
	public void getAllUsers() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("test", "test", "test", "test", "test", "test", "test", "test"));
		Mockito.when(userRepo.findAll()).thenReturn(userList);
		
		UserResponse response = loginService.getAllUsers();
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void getAllUsersFail() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("test", "test", "test", "test", "test", "test", "test", "test"));
		Mockito.when(userRepo.findAll()).thenReturn(null);
		
		UserResponse response = loginService.getAllUsers();
		
		Assert.assertEquals("FAILURE",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testSearchByUserName() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("test", "test", "test", "test", "test", "test", "test", "test"));
		Mockito.when(userRepo.searchUserByUsername(Mockito.any())).thenReturn(userList);
		
		UserResponse response = loginService.searchByUserName("test");
		
		Assert.assertEquals("SUCCESS",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testSearchByUserNameFail() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("test", "test", "test", "test", "test", "test", "test", "test"));
		Mockito.when(userRepo.searchUserByUsername(Mockito.any())).thenReturn(null);
		
		UserResponse response = loginService.searchByUserName("test");
		Assert.assertEquals("FAILURE",response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testLoginuser() {
		
		LoginRequest request = new LoginRequest(new RequestHeader(),"test","test");
		String password = TweetAppServiceUtil.encodePassword("test");
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(new User("test","test","test","test","test",password,"test","test"));
		UserResponse response = loginService.loginUser(request);
		
		Assert.assertEquals("SUCCESS", response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testLoginuserINternalServerError() {
		
		LoginRequest request = new LoginRequest(new RequestHeader(),"test","test");
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(new User("test","test","test","test","test","test","test","test"));
		UserResponse response = loginService.loginUser(request);
		
		Assert.assertEquals("FAILURE", response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testLoginuserNoDAta() {
		
		LoginRequest request = new LoginRequest(new RequestHeader(),"test","test");
		String password = TweetAppServiceUtil.encodePassword("test");
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(null);
		UserResponse response = loginService.loginUser(request);
		
		Assert.assertEquals("NO DATA FOUND", response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	
	@Test
	public void testforgotPassword() {
		
		ForgotPasswordRequest request = new ForgotPasswordRequest(new RequestHeader(),"test","test");
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(new User());
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(new User());
		UserResponse response = loginService.forgotPassword(request,"test");
		
		Assert.assertEquals("SUCCESS", response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testforgotPasswordInternalServerError() {
		ForgotPasswordRequest request = new ForgotPasswordRequest(new RequestHeader(),"test","test");		
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(new User());
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(null);
		UserResponse response = loginService.forgotPassword(request,"test");
		Assert.assertEquals("FAILURE", response.getResponseHeader().getTransactionNotification().getStatus());
	}
	
	@Test
	public void testforgotPasswordNodata() {
		ForgotPasswordRequest request = new ForgotPasswordRequest(new RequestHeader(),"test","test");		
		Mockito.when(userRepo.findByLoginId(Mockito.anyString())).thenReturn(null);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(null);
		UserResponse response = loginService.forgotPassword(request,"test");
		Assert.assertEquals("NO DATA FOUND", response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
	}
	
	@Test
	public void testRegisterUser() {
		
		
		Mockito.when(userRepo.existsByEmailId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.existsByLoginId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(new User());
		
		UserResponse response =loginService.registerUser(new RegisterRequest(new RequestHeader(),"test","test","test@test.com","ttesttttest","test","test","8989898989"));
		
		Assert.assertEquals("SUCCESS", response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	
	@Test
	public void testRegisterUserEmailPresent() {
		
		
		Mockito.when(userRepo.existsByEmailId(Mockito.any())).thenReturn(true);
		Mockito.when(userRepo.existsByLoginId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(new User());
		
		UserResponse response =loginService.registerUser(new RegisterRequest(new RequestHeader(),"test","test","test@test.com","ttesttttest","test","test","8989898989"));
		
		Assert.assertEquals("Email Id is Already in Use", response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	
	@Test
	public void testRegisterUserLoginIdPresent() {
		
		
		Mockito.when(userRepo.existsByEmailId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.existsByLoginId(Mockito.any())).thenReturn(true);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(new User());
		
		UserResponse response =loginService.registerUser(new RegisterRequest(new RequestHeader(),"test","test","test@test.com","ttesttttest","test","test","8989898989"));
		
		Assert.assertEquals("Login Id is Already in Use", response.getResponseHeader().getTransactionNotification().getRemarks().getMessages().get(0).getDescription());
		
	}
	
	
	@Test
	public void testRegisterUserINternalServerError() {
		
		
		Mockito.when(userRepo.existsByEmailId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.existsByLoginId(Mockito.any())).thenReturn(false);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(null);
		
		UserResponse response =loginService.registerUser(new RegisterRequest(new RequestHeader(),"test","test","test@test.com","ttesttttest","test","test","8989898989"));
		
		Assert.assertEquals("FAILURE", response.getResponseHeader().getTransactionNotification().getStatus());
		
	}
	
	
	
	
}
