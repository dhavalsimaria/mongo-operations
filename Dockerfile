FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} MongoOperations.jar
ENTRYPOINT ["java","-jar","/MongoOperations.jar"]