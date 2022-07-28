package com.tweetapp.model.response;

import java.util.List;

import com.tweetapp.model.common.composite.ResponseHeader;
import com.tweetapp.model.entity.Tweet;

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
public class TweetResponse {

	private ResponseHeader responseHeader = new ResponseHeader();
	private List<Tweet> data;
}
