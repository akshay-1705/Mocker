# First stage: Build stage
FROM openjdk:21-jdk-oracle AS build
WORKDIR /app

# Copy the Maven wrapper scripts and the project configuration files
COPY mvnw mvnw.cmd ./
COPY .mvn ./.mvn

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies specified in the pom.xml file
RUN ./mvnw dependency:go-offline -B

# Copy the application source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Second stage: Production-ready image
FROM openjdk:21-jdk-oracle AS production
WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=build /app/target/SocialMedia-0.0.1-SNAPSHOT.jar ./SocialMedia-0.0.1-SNAPSHOT.jar

# Set environment variables for PostgreSQL connection
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/social_media \
    SPRING_DATASOURCE_USERNAME=akshayarora \
    SPRING_DATASOURCE_PASSWORD=191168 \
    HTTP_ENDPOINT="http://worldtimeapi.org/api/timezone/Asia/Kolkata"

# Expose the port that our application runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "SocialMedia-0.0.1-SNAPSHOT.jar"]
