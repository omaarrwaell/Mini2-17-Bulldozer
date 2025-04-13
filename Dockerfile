FROM openjdk:25-ea-4-jdk-oraclelinux9
WORKDIR /app
COPY target/mini1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]