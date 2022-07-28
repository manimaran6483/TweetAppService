package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.LoginRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginRequestTest {

	@Test
	public void testLoginREqeust() {
		
		LoginRequest request = new LoginRequest();
		LoginRequest request2 = new LoginRequest(new RequestHeader(), "test", "test");
		request2.getLoginID();
		request2.getPassword();
		request2.getRequestHeader();
		
		request.setLoginID("test");
		request.setPassword("test");
		request.setRequestHeader(new RequestHeader());
		
		System.out.println(request.toString());
	}
	
	
}
