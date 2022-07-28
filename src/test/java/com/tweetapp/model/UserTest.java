package com.tweetapp.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.entity.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

	@Test
	public void testUserTest() {
		User u = new User();
		User uu = new User("test", "test", "etst", "tst", "tset", "test", "test", "Test");
		
		uu.getContactNumber();
		uu.getEmailId();
		uu.getFirstName();
		uu.getLastName();
		uu.getLoginId();
		uu.getPassword();
		uu.getRegisteredDate();
		uu.getUserId();
		
		u.setContactNumber("test");
		u.setEmailId("test");
		u.setFirstName("test");
		u.setLastName("test");
		u.setLoginId("test");
		u.setPassword("test");
		u.setRegisteredDate("test");
		u.setUserId("test");
		
		System.out.println(u.toString());
	}
	
}
