package com.tweetapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tweetapp.model.entity.Reply;
import com.tweetapp.model.entity.Tweet;

@Repository
public class TweetReplyRepository{

	@Autowired
	private DynamoDBMapper mapper;
	
	public Reply save(Reply reply) {
		mapper.save(reply);
		return reply;
	}

	
}
