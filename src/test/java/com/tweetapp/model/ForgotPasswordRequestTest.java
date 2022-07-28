package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.ForgotPasswordRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ForgotPasswordRequestTest {

	
	@Test
	public void testForgotPasswordRequest() {
		
		ForgotPasswordRequest r = new ForgotPasswordRequest();
		ForgotPasswordRequest rr = new ForgotPasswordRequest(new RequestHeader(), "test", "test");
		
		rr.getRequestHeader();
		rr.getConfirmPassword();
		rr.getPassword();
		
		r.setRequestHeader(new RequestHeader());
		r.setPassword("test");
		r.setConfirmPassword("test");
		System.out.println(r.toString());
		
	}
}
