# drone-fleet-service

## Description

This project is a Spring Boot application for managing a fleet of drones that deliver medications. The application provides a REST API that allows clients to register drones, load them with medication items, check loaded medication items for a given drone, check available drones for loading, and check the battery level of a given drone.

you can find the project requirement in [REQUIREMENT](./REQUIREMENT.md)

## Prerequisites

- Java 17 or above
- Maven
- MySQL

## Building the Project

1. Clone the repository: 
    ```bash
    git clone https://github.com/Elshaabany/drone-fleet-service.git
    ```
2. Navigate to the project directory:
    ```bash
    cd drone-fleet-service
    ```

3. Build the project: 
    ```bash
    mvn clean install
    ```

## Database Setup

Before running the application, you need to set up the database. Detailed instructions for setting up the database are provided in the [database/README.md](./database/README.md) file. Please follow these instructions before proceeding with the next steps.


## Running the Project

- Start the application:
     ```bash
        java -jar target/fleet*.jar
     ```
    OR
    ```bash
        mvn spring-boot:run
     ```

- The application will be running on: `http://localhost:8080`
- you can import the Postman collection located under [postman](./postman/) directory and use it to test the API