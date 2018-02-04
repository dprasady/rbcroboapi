# RBC Robot test Project
This project demonestrates the Robot project which has REST apis based on the specification.

## Description
This Project performs the below operations on the endpoints which are stored in the In-Memory H2 Database :
- `/robot/add` - This inserts the data into the Robot table which is created in H2, only the name field is required
- `/robot/show/{id}` - This returns the JSON payload based on the valid id
- `/robot/list` - This returns the collection of JSON payloads
- `/robot/delete/{id}` - This deletes the Robot entry based on the valid id

## Libraries used
- Spring Boot
- Spring Configuration
- Spring REST Controller
- Spring JPA
- H2
- Swagger
- Maven
- Java 8
- IntelliJ IDEA

## Compilation Command
- `mvn clean install` - Plain maven clean and install

## Run the project from command line after building the jar from the above command
-  java -jar rbc-robo-api-0.0.1-SNAPSHOT.jar

## Swagger UI. Open the Browser and run the below URL to see the API endpoints
- `http://localhost:8080/swagger-ui.html` - Click on the robot-controller hyperlink to see the endpoints



