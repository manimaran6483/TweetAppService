package com.tweetapp.constants;

public final class TweetAppConstants {

	private TweetAppConstants() {
	
	}
	
	public static final String REGISTER_PATH ="/register";
	public static final String LOGIN_PATH ="/login";
	public static final String FORGOTPASSWORD_PATH ="/{username}/forgot";
	
	public static final String INTERNAL_SERVER_ERROR_CODE ="500";
	public static final String INTERNAL_SERVER_ERROR_MSG ="INTERNAL SERVER ERROR";
	
	public static final String BAD_REQUEST_CODE ="400";
	public static final String BAD_REQUEST_MSG ="BAD REQUEST";
	
	public static final String FORBIDDEN_CODE ="403";
	public static final String FORBIDDEN_MSG ="FORBIDDEN";
	
	public static final String NO_DATA_FOUND_CODE ="404";
	public static final String NO_DATA_FOUND_MSG ="NO DATA FOUND";
	
	
	
}
