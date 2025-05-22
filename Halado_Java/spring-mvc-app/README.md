# Spring MVC Application

## Overview
This project is a Spring Boot application that follows the MVC (Model-View-Controller) architecture. It demonstrates how to create a RESTful web service with basic CRUD operations using Spring Boot, along with an in-memory database for data storage.

## Project Structure
The project is organized as follows:

```
spring-mvc-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── springmvcapp
│   │   │               ├── controller          # Contains controller classes for handling HTTP requests
│   │   │               ├── dto                 # Contains Data Transfer Object (DTO) classes
│   │   │               ├── exception            # Contains custom exception classes
│   │   │               ├── model               # Contains model classes representing the data structure
│   │   │               ├── repository           # Contains repository interfaces for data access
│   │   │               ├── service              # Contains service classes for business logic
│   │   │               └── SpringMvcAppApplication.java # Entry point of the application
│   │   └── resources
│   │       ├── application.properties           # Configuration properties for the application
│   │       └── postman
│   │           └── spring-mvc-app.postman_collection.json # Postman collection for testing
├── pom.xml                                       # Maven configuration file
└── README.md                                     # Project documentation
```

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd spring-mvc-app
   ```

2. **Build the Project**
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   You can run the application using:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   The application will be available at `http://localhost:8080`.

## Usage
The application provides several endpoints for managing entities. You can use the Postman collection located in `src/main/resources/postman/spring-mvc-app.postman_collection.json` to test the API.

## Exception Handling
Custom exceptions are defined in the `exception` package to handle specific error scenarios gracefully.

## Contribution
Feel free to contribute to this project by submitting issues or pull requests. 

## License
This project is licensed under the MIT License.