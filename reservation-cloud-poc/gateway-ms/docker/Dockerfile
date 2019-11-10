FROM maven:3.6.0-jdk-11-slim AS build

COPY reservation-cloud-poc/gateway-ms/src /home/app/reservation-cloud-poc/gateway-ms/src
COPY reservation-cloud-poc/gateway-ms/pom.xml /home/app/reservation-cloud-poc/gateway-ms
COPY reservation-cloud-poc/pom.xml /home/app/reservation-cloud-poc
COPY pom.xml /home/app

RUN mvn -f /home/app/reservation-cloud-poc/gateway-ms/pom.xml clean package

FROM hirokimatsumoto/alpine-openjdk-11
COPY --from=build /home/app/reservation-cloud-poc/gateway-ms/target/gateway-ms-1.0-SNAPSHOT.jar /usr/local/lib/gateway-ms-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/gateway-ms-1.0-SNAPSHOT.jar"]