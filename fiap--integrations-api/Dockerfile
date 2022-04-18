## PARA O CRIAR O CONTAINER COM BUILD NO CONTAINER
FROM gradle:7.4.1-jdk11

RUN mkdir /app

COPY /gradle /app/gradle
COPY /src /app/src
COPY build.gradle.kts /app/
COPY settings.gradle.kts /app/
COPY gradlew /app/
COPY gradlew.bat /app/

WORKDIR /app

RUN gradle clean build
RUN cp /app/build/libs/*.jar /app/app.jar

EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

## PARA CRIAR O CONTAINER COM BUILD LOCAL
#FROM openjdk:11.0.12-jre-slim-buster
#RUN mkdir /app
#COPY /build/libs/*.jar /app/app.jar
#EXPOSE 5000
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]