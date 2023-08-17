FROM openjdk:11-jdk-slim AS build
WORKDIR /usr/src/app
COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline
COPY src ./src/
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/DailyUpdates-0.0.1-SNAPSHOT.jar ./DailyUpdates.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","DailyUpdates.jar"]