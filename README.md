									TWEET APP SERVICE
	
Springboot application will run in port 8081.

Swagger Link - http://localhost:8081/api/v1.0/tweets/swagger-ui/index.html
	
	Configuration Needed : 

MongoDB in port 27017 with UserDetails,Tweet,TweetLike,TweetReply Collection in TweetApp DB.

Kafka server in port 9092 with topic tweetapplogs.

Logs are stored in config/logs/tweet-app-service.log file which will be the input for ELK stack to store and visualize logs. 

Application is monitored using Prometheus and Grafana.


	Steps to start Zookeeper and Kafka Server :

i)   Start zookeeper    - zookeeper-server-start.bat C:\kafka_2.12-2.4.1\config\zookeeper.properties

ii)  Start Kafka Server - kafka-server-start.bat C:\kafka_2.12-2.4.1\config\server.properties

iii) Create kafka topic - kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic tweetapplogs
