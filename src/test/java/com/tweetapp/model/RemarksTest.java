package com.tweetapp.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.atomic.Message;
import com.tweetapp.model.common.composite.Remarks;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RemarksTest {

	
	@Test
	public void testRemarks() {
		
		Remarks r = new Remarks();
		Remarks rr = new Remarks(new ArrayList<Message>());
		
		rr.getMessages();
		r.setMessages(new ArrayList<Message>());
		
		System.out.println(r.toString());
	}
}
