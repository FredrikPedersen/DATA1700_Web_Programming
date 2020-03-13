## Obligatory Assignments - Fredrik Pedersen s306631@oslomet.no

This is the project for obligatory assignments. Duh.
</br>To run the application, run the class ObligatoryassignmentApplication class and then go to http://localhost:63342/obligatoryassignment/static/html/index.html 

### Server Application

#### The server application is built using Spring Boot with the following dependencies:
 - Project Lombok for annotation based generation of boilerplate code
 - Mapstruct for annotation based generation of POJO mappers
 - H2 Database for in-memory storage of entities
 - Spring Data JPA as the persistance library
 
#### The following classes have been unit tested (poorly)
 - TicketServiceImpl
 - TicketController
 - TicketMapper
 
The application utilizes a simple repository pattern for persisting entities in a H2 database.
The REST API Controller has implemented methods for retrieving all Ticket entitities in the database (GET), creating a new ticket entity (POST) and deleting all ticket entities (DELETE).
All domain models are mapped to DTO models before being passed to the API endpoints.

*Note: The Database can be accessed at localhost:8080/h2-console*

### Client Application

#### The Client application is a Javascript Application with the following dependencies:
 - JQuery
 - Bootstrap 4 for styling
 - Axios for AJAX calls
 - Sweetalert for making pop-up boxes
 
The application consists of a form for registering a new ticket, and a table for listing all the tickets. 
When adding, retrieving or deleting tickets, the client application will call the server application's REST API endpoints. 
