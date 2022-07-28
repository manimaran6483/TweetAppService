package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.request.UserDTO;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDTOTest {

	
	@Test
	public void testUserDTOTEst() {
		
		UserDTO user = new UserDTO();
		UserDTO u = new UserDTO("test", "gtest", "test", "tste", "test", "test", "test");
		
		user.setContactNumber("test");
		user.setEmailId("test");
		user.setFirstName("tst");
		user.setLastName("test");
		user.setLoginId("test");
		user.setRegisteredDate("test");
		user.setUserId("test");
		
		u.getContactNumber();
		u.getEmailId();
		u.getFirstName();
		u.getLastName();
		u.getLoginId();
		u.getRegisteredDate();
		u.getUserId();
		
		System.out.println(u.toString());
	}
}
