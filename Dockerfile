# Use the official OpenJDK image as base
FROM openjdk:21-jdk-oracle

# Install PostgreSQL client
#RUN apk --no-cache add postgresql-client

# Set environment variables for PostgreSQL connection
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/social_media \
    SPRING_DATASOURCE_USERNAME=akshayarora \
    SPRING_DATASOURCE_PASSWORD=191168 \
    HTTP_ENDPOINT="http://worldtimeapi.org/api/timezone/Asia/Kolkata"

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/SocialMedia-0.0.1-SNAPSHOT.jar /app/SocialMedia-0.0.1-SNAPSHOT.jar

# Expose the port that our application runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "SocialMedia-0.0.1-SNAPSHOT.jar"]
