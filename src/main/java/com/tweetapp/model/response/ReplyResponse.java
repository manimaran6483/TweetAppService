package com.tweetapp.model.response;

import java.util.List;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {

	private ResponseHeader responseHeader = new ResponseHeader();
	
	private List<Reply> data;
	
}
