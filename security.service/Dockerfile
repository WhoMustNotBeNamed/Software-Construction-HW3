FROM amazoncorretto:21-alpine3.19

WORKDIR /app
ADD build/libs/security.service-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
