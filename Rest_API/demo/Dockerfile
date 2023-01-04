FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/demo-0.0.1.jar rest_api.jar
ENTRYPOINT ["java","-jar","/rest_api.jar"]