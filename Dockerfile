FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG CloudRunJobPOC-0.0.1-SNAPSHOT.jar
COPY ${CloudRunJobPOC-0.0.1-SNAPSHOT.jar} app.jar
ENTRYPOINT ["java","-jar","/CloudRunJobPOC-0.0.1-SNAPSHOT.jar"]