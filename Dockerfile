FROM java:8-jdk-alpine
COPY ./build/libs/geoserver-0.1.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch geoserver-0.1.0.jar'
ENTRYPOINT ["java","-jar","geoserver-0.1.0.jar"]