FROM maven:3.6.0-jdk-8-slim AS build
COPY src /mongo-operations/src
COPY pom.xml /mongo-operations
RUN mvn -f /mongo-operations/pom.xml clean package

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/MongoOperations-1.0.jar
COPY ${JAR_FILE} MongoOperations-1.0.jar
ENTRYPOINT ["java","-jar","/MongoOperations-1.0.jar"]
