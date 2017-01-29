### What is it?

This is a scaffolding for futures Spring projects and related studies.

### Features
- Login/logout with Spring Security and database authentication
- Flyway migrations
- Maven lean pom


### Requirements:

- MySql
- Java 8+
- Maven
- Tomcat 7+

### To execute the first build:

1. Create database 'visit_card'
2. Configure the database access in pom.xml, it`s necessary to allow flyway access the database
3. mvn compile flyway:migrate
4. mvn clean install 


### Construct the project to work on Eclipse IDE:

$ mvn eclipse:eclipse -Dwtpversion=2.0
