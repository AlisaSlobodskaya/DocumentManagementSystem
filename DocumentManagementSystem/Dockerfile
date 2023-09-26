# syntax=docker/dockerfile:1

FROM openjdk:16-alpine3.13

EXPOSE 8080

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]