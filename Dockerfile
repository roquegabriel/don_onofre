#FROM openjdk:17-alpine
#COPY target/onofre-0.0.1-SNAPSHOT.jar onofre.jar
# ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","onofre.jar"]


FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/onofre-0.0.1-SNAPSHOT.jar onofre.jar
EXPOSE 8080
ENTRYPOINT ["Java","-jar", "onofre.jar"]

