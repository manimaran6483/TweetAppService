package com.tweetapp.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tweetapp.model.entity.Tweet;
import com.tweetapp.model.entity.TweetLike;

@Repository
public class TweetLikeRepository{

	@Autowired
	private DynamoDBMapper mapper;
	
	public TweetLike save(TweetLike tweetlike) {
		mapper.save(tweetlike);
		return tweetlike;
	}
	
	public Optional<TweetLike> findById(String tweetLikeId) {
		Optional<TweetLike> opt = Optional.ofNullable(mapper.load(TweetLike.class, tweetLikeId));
		return opt;
	}
	
	public void deleteById(String tweetLikeId) {
        mapper.delete(tweetLikeId);
 }

	
}
