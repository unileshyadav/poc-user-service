FROM maven:3.6.3-jdk-11
WORKDIR /build
COPY . .
RUN mvn clean compile package

FROM openjdk:13-jdk-alpine
VOLUME /tmp
COPY --from=0 /build/target/*.war app.war
ENV JAVA_OPTS=""
EXPOSE 8282
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.war" ]