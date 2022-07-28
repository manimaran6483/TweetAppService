package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.RequestHeader;
import com.tweetapp.model.request.RegisterRequest;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterRequestTest {

	
	@Test
	public void testRegisterRequest() {
		RegisterRequest request = new RegisterRequest();
		RegisterRequest request2 = new RegisterRequest(new RequestHeader(),"test","test","test@test.com","testtestst","test","test","9999999999");
		request2.getConfirmPassword();
		request2.getContactNumber();
		request2.getEmailId();
		request2.getFirstName();
		request2.getLastName();
		request2.getLoginId();
		request2.getPassword();
		request2.getRequestHeader();
		
		request.setConfirmPassword("test");
		request.setContactNumber("test");
		request.setEmailId("test");
		request.setFirstName("test");
		request.setLastName("test");
		request.setLoginId("test");
		request.setPassword("test");
		request.setRequestHeader(new RequestHeader());
		System.out.println(request.toString());
		
		
	}
}
