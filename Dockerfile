FROM maven:3.5-jdk-8-alpine
ADD ./ ./
RUN mvn package -DskipTests
EXPOSE 8081
EXPOSE 9092
EXPOSE 9001
CMD ["java", "-jar", "target/InciDashboard-0.0.1.jar"]
