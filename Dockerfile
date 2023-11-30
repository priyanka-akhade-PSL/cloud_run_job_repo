#
# Package stage
#
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar
#EXPOSE 8080
ENTRYPOINT java -jar /app/runner.jar

# FROM eclipse-temurin:17-jdk-alpine
# VOLUME /tmp
# ARG CloudRunJobPOC-0.0.1-SNAPSHOT.jar
# COPY ${CloudRunJobPOC-0.0.1-SNAPSHOT.jar} app.jar
# ENTRYPOINT ["java","-jar","/CloudRunJobPOC-0.0.1-SNAPSHOT.jar"]