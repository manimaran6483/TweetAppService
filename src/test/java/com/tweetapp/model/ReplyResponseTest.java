package com.tweetapp.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Reply;
import com.tweetapp.model.response.ReplyResponse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplyResponseTest {

	
	@Test
	public void testReplyRespone() {
		
		ReplyResponse r = new ReplyResponse();
		ReplyResponse rr = new ReplyResponse(new ResponseHeader(), new ArrayList<Reply>());
		
		rr.getData();
		rr.getResponseHeader();
		
		r.setData(new ArrayList<Reply>());
		r.setResponseHeader(new ResponseHeader());
		System.out.println(r.toString());
	}
}
