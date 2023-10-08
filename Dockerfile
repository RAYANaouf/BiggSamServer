# Use a base image with the desired Java version
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app


# Copy the JAR file to the container
COPY build/libs/app.jar /app/app.jar

# Expose the port on which the Ktor application will listen
EXPOSE 8080

# Start the Ktor application when the container is run
CMD ["java", "-jar", "app.jar"]