FROM maven:3.6.0-jdk-11-slim AS build
COPY reservation-cloud-poc/eureka-server/src /home/app/reservation-cloud-poc/eureka-server/src
COPY reservation-cloud-poc/eureka-server/pom.xml /home/app/reservation-cloud-poc/eureka-server
COPY reservation-cloud-poc/pom.xml /home/app/reservation-cloud-poc
COPY pom.xml /home/app
RUN mvn -f /home/app/reservation-cloud-poc/eureka-server/pom.xml clean package

FROM hirokimatsumoto/alpine-openjdk-11
COPY --from=build /home/app/reservation-cloud-poc/eureka-server/target/eureka-server-1.0-SNAPSHOT.jar /usr/local/lib/eureka-server-1.0-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/usr/local/lib/eureka-server-1.0-SNAPSHOT.jar"]