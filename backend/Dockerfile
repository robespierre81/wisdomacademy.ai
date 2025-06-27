FROM maven:3.9.5-eclipse-temurin-17

EXPOSE 8585
WORKDIR /app
COPY . /app

# Build the application
RUN mvn clean package

ENV JAVA_OPTS="-Xms512m -Xmx1024m"


ENTRYPOINT ["java", "-jar", "/app/target/backend-1.0.jar"]
