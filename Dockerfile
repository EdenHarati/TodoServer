FROM openjdk:8
WORKDIR /app
COPY target/exe3-server-edenharati-313198939-final-1.0-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "exe3-server-edenharati-313198939-final-1.0-SNAPSHOT.jar"]