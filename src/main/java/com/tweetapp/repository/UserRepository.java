package com.tweetapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.model.entity.User;

@Repository
public class UserRepository {

	@Autowired
	private DynamoDBMapper mapper;
	
	public Optional<User> findById(String userId) {
		Optional<User> opt = Optional.ofNullable(mapper.load(User.class, userId));
		return opt;
	}

	public User findByLoginId(String username) {
		List<User> results = mapper.scan(User.class, new DynamoDBScanExpression());
		User user = null;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().equalsIgnoreCase(username)) {
				user = results.get(i);
			}
		}
		return user;
	}
	
	public boolean existsByLoginId(String username) {
		List<User> results = mapper.scan(User.class, new DynamoDBScanExpression());
		User user = null;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().equalsIgnoreCase(username)) {
				user = results.get(i);
			}
		}
		return user!=null ? true: false;
	}
	
	public boolean existsByEmailId(String email) {
		List<User> results = mapper.scan(User.class, new DynamoDBScanExpression());
		User user = null;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getEmailId().equalsIgnoreCase(email)) {
				user = results.get(i);
			}
		}
		return user!=null ? true: false;
	}

	public User save(User user) {
		mapper.save(user);
		return user;
	}

	public List<User> findAll() {
		return mapper.scan(User.class, new DynamoDBScanExpression());
	}

	public List<User> searchByUsername(String username) {
		List<User> results = mapper.scan(User.class, new DynamoDBScanExpression());
		List<User> returnedList = new ArrayList<User>();
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getLoginId().contains(username)) {
				returnedList.add(results.get(i));
			}
		}
		return returnedList;
	}

//	@Query("{loginId:{$regex:?0,$options:'i'}}")
//	List<User> searchUserByUsername(String userName);
//	
////	@Query("{loginId:{$in:?0}}")
////	List<User> findLoginIdByUserIds(List<String> userIds);
//	
//	User findByLoginId(String loginId);
//	
//	boolean existsByLoginId(String loginId);
//	
//	boolean existsByEmailId(String emailId);
}
