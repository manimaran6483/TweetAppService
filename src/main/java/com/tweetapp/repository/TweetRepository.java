package com.tweetapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.model.entity.Tweet;

@Repository
public class TweetRepository{

//	List<Tweet> findAllByTweetId(String TweetId);

	
	@Autowired
	private DynamoDBMapper mapper;
	
	public Optional<Tweet> findById(String TweetId) {
		Optional<Tweet> opt = Optional.ofNullable(mapper.load(Tweet.class, TweetId));
		return opt;
	}

	public Tweet findByLoginId(String Tweetname) {
		List<Tweet> results = mapper.scan(Tweet.class, new DynamoDBScanExpression());
		Tweet Tweet = null;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().equalsIgnoreCase(Tweetname)) {
				Tweet = results.get(i);
			}
		}
		return Tweet;
	}
	
	public boolean existsByLoginId(String loginId) {
		List<Tweet> results = mapper.scan(Tweet.class, new DynamoDBScanExpression());
		Tweet Tweet = null;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().equalsIgnoreCase(loginId)) {
				Tweet = results.get(i);
			}
		}
		return Tweet!=null ? true: false;
	}

	public Tweet save(Tweet Tweet) {
		mapper.save(Tweet);
		return Tweet;
	}

	public List<Tweet> findAll() {
		return mapper.scan(Tweet.class, new DynamoDBScanExpression());
	}

	public List<Tweet> findAllByUserId(String userId) {
		
		List<Tweet> results = mapper.scan(Tweet.class, new DynamoDBScanExpression());
		List<Tweet> returnedList = new ArrayList<Tweet>();
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getUserId().equalsIgnoreCase(userId)) {
				returnedList.add(results.get(i));
			}
		}
		return returnedList;
	}
	
	public List<Tweet> searchByTweetname(String Tweetname) {
		List<Tweet> results = mapper.scan(Tweet.class, new DynamoDBScanExpression());
		List<Tweet> returnedList = new ArrayList<Tweet>();
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().contains(Tweetname)) {
				returnedList.add(results.get(i));
			}
		}
		return returnedList;
	}
	
	 public void deleteById(String tweetId) {
	        mapper.delete(tweetId);
	 }
}
