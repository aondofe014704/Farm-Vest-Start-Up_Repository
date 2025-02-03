FROM maven:3.8.7 AS build
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17
COPY --from=build target/*.jar farm-vest.jar

ENTRYPOINT ["java", "-Xmx28m", "-jar", "-Dserver.port=8080", "farm-vest.jar"]