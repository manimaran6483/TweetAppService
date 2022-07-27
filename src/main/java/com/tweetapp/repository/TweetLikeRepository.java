package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.entity.TweetLike;

public interface TweetLikeRepository extends MongoRepository<TweetLike, String> {

}
