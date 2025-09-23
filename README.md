# Health connection

A Java Spring Boot application with Maven and SQL integration. Backend application for dynamic health notifications: generates, customizes, and sends messages via WhatsApp with AI support for topic selection, content personalization, and scheduling as defined by the user.

## Features

- RESTful API built with Spring Boot
- Database integration using SQL
- Maven for dependency management

## Requirements

- Java 17+
- Maven
- A running SQL database (PostgreSQL)

## Getting Started

1. **Clone the repository:**
   git clone https://github.com/Postech-Code-Wizards/conexaosaude. 

2. **Configure the database:**
      - Edit `src/main/resources/application.properties` with your database credentials.

3. **Build the project:**
   mvn clean install

4. **Run the application:**
      - mvn spring-boot:run

## Usage

- Access the API at `http://localhost:8080`

## Project Structure

- `src/main/java` - Java source code
- `src/main/resources` - Configuration files
- `src/test/java` - Unit and integration tests

## License

This project is licensed under the MIT License.