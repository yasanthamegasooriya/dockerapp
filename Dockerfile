#FROM eclipse-temurin:17
#ARG JAR_FILE=target/*.jar
#COPY ./target/SpringtimeFun-0.0.1.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/SpringtimeFun-0.0.1.jar"]
