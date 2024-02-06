## Newcastle - A web App to suggest touristic attractions in the city of Newcastle

Java, Spring Boot, JavaScript, HTML, CSS, MySQL, Amazon Web Services

This group project gave me insight into the software engineering workflow.

My resposibility was the implementation of the backend Java SpringBoot code. 

API related code and formatting of Google Maps data in the backend into usable information for the front-end team to display. In charge of Implementing the RDS database schema.
Lead the communication between frontend and backend teams to ensure proper functionality of formatted API data.

## Structure

src/main/java/com/team21/attractionsGuide/controller - deal with requests and respond JSON bodies.

src/main/java/com/team21/attractionsGuide/entity - models related to database and http response struct

src/main/java/com/team21/attractionsGuide/error - customized error

src/main/java/com/team21/attractionsGuide/repository - interfaces. repository layer

src/main/java/com/team21/attractionsGuide/service - connection between repository and controller. They also process external APIs.

src/main/resources - configurations

src/test - Unit test

docs/index.html - Class documentation index

## Start project

### Change configuration

Local development configuration: src/main/resources/config/application-dev.yml

Production configuration: src/main/resources/config/application-pro.yml

To start in your own environment, change the database configuration.

deployment: https://16.16.179.85.nip.io/  Click this link to see the deployment

### Run

```shell
mvn -N io.takari:maven:wrapper
mvn spring-boot:run
```


