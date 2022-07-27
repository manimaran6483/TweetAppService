package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.entity.Reply;

public interface TweetReplyRepository extends MongoRepository<Reply, String> {

}
