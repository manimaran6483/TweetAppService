package com.tweetapp.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.request.UserDTO;
import com.tweetapp.model.response.UserResponse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserResponseTest {

	
	@Test
	public void testUserResponse() {
		
		UserResponse r = new UserResponse();
		UserResponse rr = new UserResponse(new ResponseHeader(), new ArrayList<UserDTO>());
		
		rr.getData();
		rr.getResponseHeader();
		
		r.setData(new ArrayList<UserDTO>());
		r.setResponseHeader(new ResponseHeader());
		
		System.out.println(r.toString());
	}
}

