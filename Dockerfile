FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/socle-0.0.1-SNAPSHOT.jar socle.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "socle.jar"]
# Build the Docker image

