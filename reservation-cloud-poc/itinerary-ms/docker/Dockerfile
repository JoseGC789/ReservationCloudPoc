FROM maven:3.6.0-jdk-11-slim AS build

COPY reservation-cloud-poc/itinerary-ms/src /home/app/reservation-cloud-poc/itinerary-ms/src
COPY reservation-cloud-poc/itinerary-ms/pom.xml /home/app/reservation-cloud-poc/itinerary-ms
COPY reservation-cloud-poc/pom.xml /home/app/reservation-cloud-poc
COPY pom.xml /home/app

RUN mvn -f /home/app/reservation-cloud-poc/itinerary-ms/pom.xml clean package

FROM hirokimatsumoto/alpine-openjdk-11
COPY --from=build /home/app/reservation-cloud-poc/itinerary-ms/target/itinerary-ms-1.0-SNAPSHOT.jar /usr/local/lib/itinerary-ms-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/itinerary-ms-1.0-SNAPSHOT.jar"]