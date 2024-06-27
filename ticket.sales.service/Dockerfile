FROM amazoncorretto:21-alpine3.19

WORKDIR /ticket-sales-service
ADD build/libs/ticket.sales.service-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
