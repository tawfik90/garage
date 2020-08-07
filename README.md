# COGNIZANT Assignment
Hello everyone,

I created this app for a demo, it is not full project because of my time but really I enjoyed it

## Intro
this application represents a store for set of vehicles. 
It will hold all records in H2 database and provide REST API.

my structure
    
    garage
    |
    |- business
    |   |- domain
    |   |- service
    |- data
    |   |- entity
    |   |- repository
    |- web 
    |   |- filter
    |   |- congig
    |   |- service
    |   |- exception
    |- exception

   
### Assumptions:
1- The requirements and data modelling of the get API used to “list all available cars in Frank’s car” in phase one are derived from the Mock JSON: https://api.jsonbin.io/b/5ebe673947a2266b1478d892
2- Based on the statement “You need to write the Back-End API code”, it is assumed that the “frontend” within the followed statement “Unit test coverage for both frontend and backend” was not indented to cover front-end requirement.


## The Technology and framework I used as listed here:

* java 8
* Spring Boot
 
## Environment Requirements

1. JDK 1.8
2. Maven 

## How to run the application

You should go to project workspace, It called garage and run this command:

    ./mvnw spring-boot:run 

Or you can run the jar file directly

    java -jar <jar-file-path>

The app by default will start with "dev" profile in "dev" I load all the data from mock data and insert it in the database
if you want to start with empty database you can run this command line

    java -jar --spring.profiles.active=prod <jar-file-path>

Command lines above will start the app with the default port 8080.

## how to access Swagger documentation

After running the application you can access the API Documentation through this link:
    
    http://localhost:8080/swagger-ui.html

### Dependencies
* Spring
* Lombok
* SwaggerUI
  
Thanks,

A.Tawfik
