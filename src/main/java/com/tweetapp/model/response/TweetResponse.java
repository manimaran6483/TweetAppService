package com.tweetapp.model.response;

import java.util.List;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse {

	private ResponseHeader responseHeader = new ResponseHeader();
	private List<Tweet> data;
}
