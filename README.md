# Online Cinema Platform REST API
This project is a comprehensive REST API built on Spring Boot, designed to power an online cinema platform. It enables various CRUD operations for movies, session management, authentication, authorization, and more.
## Project Description
The Online Cinema Platform REST API is developed as a backend solution for an online cinema platform. It provides a range of features to manage movies, sessions, and user interactions. The API is designed with a focus on security, usability, and extensibility. It incorporates Spring Boot's capabilities to offer robust functionality.## Features
- Creation and management of consumer threads responsible for assembling cars.
- Creation and management of producer threads responsible for producing car parts (body, engine, accessory).
- User interface for controlling the speed of individual producers and the consumer.
- Real-time visualization of the assembly process using JavaFX graphical components.

## Features
- Movie CRUD Operations (Admin): Allows administrators to perform Create, Read, Update, and Delete operations on movies.
- Session Management: Facilitates the creation, viewing, and booking of cinema sessions.
- Authentication and Authorization: Utilizes JSON Web Tokens (JWT) for user authentication and authorization, ensuring secure access to API endpoints.
- Validation: Implements input validation and error handling to enhance the robustness of the API.
- Database Integration: Utilizes a MySQL database to store and manage movie, session, and user information.

## Usage
To build and run the application, follow these steps:

1. Clone the repository <br>
``git clone https://github.com/bassedOutt/Car-Assembly.git``
2. Configure Database:<br> Update the database configuration in application.properties to match your MySQL database settings.
3. Use Maven to build and run the application: mvn spring-boot:run
4. API Documentation:<br>Access the API documentation at http://localhost:8080/swagger-ui.html for detailed information about available endpoints and their usage.

