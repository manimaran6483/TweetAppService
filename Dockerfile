FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
COPY ./target/tweetapp.jar tweetapp.jar 
ENTRYPOINT ["java","-jar","/tweetapp.jar"]