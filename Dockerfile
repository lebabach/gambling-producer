FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/gamblingproducer-0.0.4-SNAPSHOT.jar
COPY ${JAR_FILE} gamblingproducer-0.0.4-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gamblingproducer-0.0.4-SNAPSHOT.jar"]
