# Personalized Data API

This is an API service that provides shoppers personalized information to eCommerce servers. The service consists of two main parts:

1. Interface with data team (internal) - Receiving shoppers' personalized information and product metadata from the data team and storing it in a specified database.
2. Interface with eCommerce (external) - Provide fast read operation for the shoppers' personalized information.

## Technologies

- Java
- Spring Boot
- MySQL
- Redis
- Gradle
- Docker
- Docker Compose
- JUnit
- Mockito

### Prerequisites

You will need to have the following tools installed on your machine:

- Java
- Gradle
- Docker
- Docker Compose

### Running the Application

1. Clone the repository to your local machine.

2. Navigate to the root directory of the project.

3. Run the following command to build the application:

   ```
   gradle clean build
   ```

4. Run the following command to start MySQL and Redis using Docker Compose:

   ```
   docker-compose up
   ```

5. Open the project in IntelliJ IDEA.

6. Run the main application class.

7. The application should now be running on `http://localhost:8080`.