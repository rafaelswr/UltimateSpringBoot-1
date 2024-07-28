### Overview 
Despite already having knowledge about Java, and the development of simple and basic restful api's, using the spring framework in other repo's, this serves to restart the learning that stopped in recent months due to other academic projects.

The objective is to explore and better understand the mechanics of developing applications with these technologies, and learn more about the use of microservices and containers and the resources provided by the framework.

Talking about this repository, it covers the theoretical part about beans, dependency injection, profile usage, Spring Data JPA, restful API development, DTO patterns, data validation, test isolation with Mockito and so on.

## Project Info, Restful API
Develop a simple Restful Api, with 3 related entities, including:
Student, School, and Student Profile.

The architecture:

![Student is the central entity that has a 1-1 relationship with the student profile and 1-N with the school.](src/main/resources/DB_design.jpg).

## Tecnologies & Tools 
Java 17, Postman, IntelliJ, PostgreSQL (by CMD & pgAdmin4), Spring Boot 3, Maven; 

## Dependencies 
Spring Boot, Spring Data JPA, Lombok, Spring Web, devtools, postgresql
