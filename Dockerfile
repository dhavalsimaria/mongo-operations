FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/MongoOperations-1.0.jar
COPY ${JAR_FILE} MongoOperations-1.0.jar
ENTRYPOINT ["java","-jar","/MongoOperations-1.0.jar"]
