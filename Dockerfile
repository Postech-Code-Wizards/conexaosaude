FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY ./pom.xml ./
COPY ./src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

COPY --from=build /app/target/*-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar /app/app.jar"]
