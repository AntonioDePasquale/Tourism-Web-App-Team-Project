## Newcastle - A web App to suggest touristic attractions in the city of Newcastle

Technologies: Java, Spring Boot, JavaScript, HTML, CSS, MySQL, Amazon Web Services

This group project gave me insight into the software engineering life cycle.

My main resposibility was the implementation of the backend Java SpringBoot code. 

API related code and formatting of Google Maps data in the backend into usable information for the front-end team to display. In charge of Implementing the RDS database schema.
Lead the communication between frontend and backend teams to ensure proper functionality of formatted API data.

## Personal Overview / My role in the project

Team Role: Backend Team Lead and API Developer

Outcome: As the backend team lead, I oversaw and managed all members of the backend team, ensuring the successful implementation of a Newcastle-based tourism web app using Java Spring Boot. The project achieved an 88% score, seamlessly integrating Google Maps API data with front-end components.

Agile Development Approach:

Sprint Structure: We adopted an Agile methodology with 2-week sprints to effectively manage the project. Each sprint began with comprehensive planning sessions where goals and tasks were defined. Instead of daily stand-ups, we held weekly in-person meetings to review progress, address challenges, and adjust our strategy as needed.
Teamwork and Collaboration:

Leadership Role: In my capacity as the backend team lead, I orchestrated and managed all aspects of backend development. This involved closely collaborating with the front-end team to ensure alignment and integration of APIs with the UI.

Supporting Team Members: Throughout the project, I demonstrated a commitment to teamwork and support for my colleagues. For instance, when a team member faced difficulties setting up the database for the comment classes, I provided them with initial code snippets I had written and conducted a detailed demo. This hands-on approach helped them grasp the concepts quickly and integrate smoothly with our codebase.

Effective Communication: I actively participated in team meetings, offering valuable feedback and insights to enhance our project's progress. My availability to answer questions and provide guidance on backend development and coding practices ensured a collaborative atmosphere where team members could learn from each other.

Experience with Software Engineering Practices:

Technical Expertise: My role encompassed leveraging Java Spring Boot and integrating RESTful APIs to enhance the application's functionality. This experience deepened my understanding of backend development practices, including API design, database management, and scalability considerations.

Problem Solving and Adaptability: Throughout the project, I addressed challenges related to API optimisation and data management, demonstrating strong problem-solving skills. Agile principles guided our iterative approach, allowing us to adapt to evolving requirements and deliver valuable updates iteratively.

Reflection on Team Experience:
This project provided invaluable insights into collaborative software development within a team setting. As the backend team lead, I fostered a culture of transparency, collaboration, and continuous improvement. By leveraging Agile principles and effective communication channels, we successfully navigated challenges and achieved project goals. The experience enhanced my leadership skills and technical proficiency, reinforcing the importance of structured communication, teamwork, and iterative development in delivering impactful software solutions.


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


