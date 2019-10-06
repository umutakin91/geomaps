# geomaps

Welcome to geomaps API.

### Running it Locally
To build the executable jar you can execute the following command:
```
./gradlew bootJar
```
And run it as:
```
java -jar build/libs/geoserver-0.1.0.jar
```
Alternativeliy, to run the application you can directly execute this command:
```
./gradlew bootRun
```

### Running with Docker

To build with docker:
```
docker build -t geonames .
```
To run in docker container:
```
docker run -p 8090:8080 geonames
```