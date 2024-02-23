FROM openjdk:17-alpine
COPY target/onofre-0.0.1-SNAPSHOT.jar onofre.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","onofre.jar"]