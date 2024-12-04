
# Warehouse Management System

## Project Overview
This is a comprehensive Warehouse Management System developed as a final graduation project. The application provides robust functionality for managing warehouse operations with secure authentication and data management.

## Technologies Used
- **Backend Framework**: Spring Boot 3.3.5
- **Java Version**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Security**: Spring Security
- **Documentation**: SpringDoc OpenAPI
- **Authentication**: JWT (JSON Web Token)

## Key Dependencies
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot Starter Security
- PostgreSQL Driver
- ModelMapper
- Lombok
- SpringDoc OpenAPI
- JWT Authentication

## Features
- Secure user authentication
- Warehouse inventory management
- RESTful API endpoints
- Comprehensive validation
- OpenAPI documentation

## Prerequisites
- Java 21
- Maven
- PostgreSQL Database

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Jaxongirshoh/warehouse.git
cd warehouse
```

### 2. Configure Database
Update `application.properties` with your PostgreSQL database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

## API Documentation
Once the application is running, you can access the Swagger UI for API documentation at:
```
http://localhost:8080/swagger-ui.html
```

## Authentication
The application uses JWT (JSON Web Token) for authentication. Obtain a token through the login endpoint and include it in the Authorization header for protected endpoints.

## Testing
Run the test suite using Maven:
```bash
mvn test
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
This project is open-source. Please check the LICENSE file for details.

## Contact
Project Maintainers: [[Jaxongir](https://github.com/Jaxongirshoh),[Ozodbek](https://github.com/devhawk7)]
Project Link: [https://github.com/Jaxongirshoh/warehouse](https://github.com/Jaxongirshoh/warehouse)
