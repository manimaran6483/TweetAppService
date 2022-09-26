package com.tweetapp.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

	@Value("${dynamodb.endpoint}")
    public String SERVICE_ENDPOINT;
	@Value("${dynamodb.region}")
    public String REGION;
	@Value("${dynamodb.accesskey}")
    public String ACCESS_KEY;
	@Value("${dynamodb.secretkey}")
    public String SECRET_KEY;

	private Base64.Decoder decoder = Base64.getMimeDecoder();
	
    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    private AmazonDynamoDB amazonDynamoDBConfig() {
    	String accessKey = new String(decoder.decode(ACCESS_KEY)); 
    	String secretKey = new String(decoder.decode(SECRET_KEY)); 
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
    }
}