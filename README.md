# people-list-hibernate 
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/mauroalfaro/people-list-hibernate)](https://github.com/mauroalfaro/people-list-hibernate/releases/tag/1.0)

## Description
people-list project with CRUD operations impacting a MySQL database.

## Design
Basic Spring application with 3 controllers, developed with the objective to help aspiring Spring developers to learn basic Spring features.
Includes:
- Spring MVC
- Spring Boot
- Spring Boot Test
- Mockito for MVC unit testing
- Java 8 lambdas
- Hibernate framework for data 
- OpenApi UI to test the app

## Using the app
In order to test the app, first you have to start up a MySQL instance on your local. You can do it using XAMPP which is the simplest way. You can follow these tutorials:

https://blog.templatetoaster.com/xampp-phpmyadmin/ (EN)
https://www.ionos.es/digitalguide/servidores/herramientas/instala-tu-servidor-local-xampp-en-unos-pocos-pasos/ (ES)

Be sure to start the MySQL server pointing to the default MySQL port (3036), which is the port expected by the Hibernate configuration. You can use MySQL Workbench or any other client to test connection.

To build the app without tests, run:

```bash
 mvn clean install -DskipTests=true
```

And then execute
```bash
java -jar people-list-hibernate/target/people-list-hibernate-1.0-RELEASE.jar
```

The API provides endpoints for the three models available: Customers, Employees and Stores. Providing CRUD operations.
You can check the Swagger UI for documentation about these endpoints. Hit http://localhost:8080/swagger-ui.html

## Run the tests
Run
```bash
mvn clean test
```
