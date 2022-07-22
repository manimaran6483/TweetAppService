package com.tweetapp.constants;

public final class TweetAppConstants {

	private TweetAppConstants() {
	
	}
	
	// LOGIN URL'S
	public static final String REGISTER_PATH ="/register";
	public static final String LOGIN_PATH ="/login";
	public static final String FORGOTPASSWORD_PATH ="/{username}/forgot";
	
	//TWEET URL'S
	public static final String GET_ALL_USERS_PATH ="/users/all";
	public static final String GET_ALL_TWEETS_PATH ="/all";
	public static final String POST_TWEET_PATH ="/{username}/add";
	public static final String UPDATE_TWEET_PATH ="/{username}/update/{id}";
	public static final String DELETE_TWEET_PATH ="/{username}/delete/{id}";
	public static final String LIKE_TWEET_PATH ="/{username}/like/{id}";
	public static final String REPLY_TWEET ="/{username}/reply/{id}";
	public static final String SEARCH_USER_PATH ="/user/search/{username}";
	public static final String GET_TWEETS_OF_USER_PATH ="/{username}";
	
	
	
	public static final String INTERNAL_SERVER_ERROR_CODE ="500";
	public static final String INTERNAL_SERVER_ERROR_MSG ="INTERNAL SERVER ERROR";
	
	public static final String BAD_REQUEST_CODE ="400";
	public static final String BAD_REQUEST_MSG ="BAD REQUEST";
	
	public static final String FORBIDDEN_CODE ="403";
	public static final String FORBIDDEN_MSG ="FORBIDDEN";
	
	public static final String NO_DATA_FOUND_CODE ="404";
	public static final String NO_DATA_FOUND_MSG ="NO DATA FOUND";
	
	public static final String INVALID_PASSWORD_CODE = "601";
	public static final String INVALID_PASSWORD ="Password is Invalid";
	
	public static final String EMAIL_IN_USE_CODE = "602";
	public static final String EMAIL_IN_USE_MSG ="Email Id is Already in Use";
	
	public static final String LOGINID_IN_USE_CODE = "603";
	public static final String LOGINID_IN_USE_MSG ="Login Id is Already in Use";
}
