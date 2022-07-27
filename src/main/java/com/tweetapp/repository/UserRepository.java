package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{loginId:{$regex:?0,$options:'i'}}")
	List<User> searchUserByUsername(String userName);
	
	User findByLoginId(String loginId);
	
	boolean existsByLoginId(String loginId);
	
	boolean existsByEmailId(String emailId);
}
