package com.tweetapp.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * This class configures the Kafka Listener to consume
 * message from Kafka topic.
 * @author Manimaran
 *
 */



@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	private static final Logger LOGGER = LogManager.getLogger(KafkaConsumerConfig.class);

	/**
	This method consumes message from kafka topic
	@param message
	*/
	
	@KafkaListener(topics = "${tweetapp.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(String message) {
		LOGGER.debug(message);
	}

}
