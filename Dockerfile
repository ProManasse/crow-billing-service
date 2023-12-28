FROM openjdk:17
VOLUME /tmp
EXPOSE 9004
COPY target/crow-billing-service-0.0.1-SNAPSHOT.jar doc-bill.jar
ENTRYPOINT ["java","-jar","/doc-bill.jar"]
