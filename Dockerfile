FROM java:8-jdk-alpine
COPY ./build/libs/geoserver-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch geoserver-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","geoserver-0.0.1-SNAPSHOT.jar"]