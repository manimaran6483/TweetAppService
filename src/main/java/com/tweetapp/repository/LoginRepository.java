package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.entity.User;

@Repository
public interface LoginRepository extends MongoRepository<User, String> {

}
