package com.tweetapp.model.response;

import java.util.List;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {

	private ResponseHeader responseHeader = new ResponseHeader();
	
	private List<Reply> data;
	
}
