# springboot-bookstore-api
Bookstore Management System Backend A secure, RESTful backend API for managing a bookstore, built with Spring Boot. Features include JWT-based authentication and role-based authorization (Admin, Manager, Customer), CRUD operations for books and authors, and pagination support.

# Bookstore Management Backend - Spring Boot Project

## Overview
This backend application, developed with Spring Boot, provides secure REST APIs to manage a bookstore system.
It supports managing books, authors, orders, and users with role-based access control.
Authentication is implemented using JWT tokens for stateless security.
API documentation and testing are provided through Swagger UI.


## Key Features

User registration and login with JWT authentication
Role-based access control for ADMIN, MANAGER, and CUSTOMER
CRUD operations on books, authors, and orders
Secure endpoints with Spring Security and a custom JWT filter
Stateless REST API design
API documentation via Swagger/OpenAPI
Password encryption using BCryptPasswordEncoder
Public endpoints available for authentication and API docs

---

## Technologies Used

- Java 17+  
- Spring Boot 3.x  
- Spring Security (with JWT)  
- Spring Data JPA with Hibernate  
- PostgreSQL database
- Maven for build management  
- Swagger/OpenAPI (springdoc-openapi)  
- Lombok for boilerplate reduction  

---

## Project Structure

- `com.bookstore.controller` - REST controllers handling HTTP requests  
- `com.bookstore.service` - Business logic and service layer  
- `com.bookstore.repository` - JPA repositories interacting with the database  
- `com.bookstore.config` - Security configuration, JWT filter, and application configs  
- `com.bookstore.model` - Entity classes (User, Book, Author, Order, etc.)  

---

## Prerequisites

- Java JDK 17 or later  
- Maven 3.x  
- MySQL database (or H2 for testing)  
- Git (for cloning repository)  

---

## Setup Instructions

### 1. Clone the repository
git clone https://github.com/mr-kamlesh-527/springboot-bookstore-api.git
cd springboot-bookstore-api
2. Configure the database
Modify src/main/resources/application.properties:

**properties**
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstoredb
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


3. Build the project
mvn clean install

**Running the Application**
Run via Maven:

mvn spring-boot:run
Application will start on: http://localhost:8085

**Using the APIs**
Public Endpoints
POST /api/auth/register - Register new user
POST /api/auth/login - Login and receive JWT token

**Secured Endpoints (Require JWT token in Authorization header)**
/api/books/** - Access books (roles: ADMIN, MANAGER, CUSTOMER)
/api/authors/** - Manage authors (role: ADMIN)
/api/admin/** - Admin-specific APIs (role: ADMIN)
/api/manager/** - Manager-specific APIs (role: MANAGER)
/api/customer/** - Customer-specific APIs (role: CUSTOMER)

**Include header:**
makefile
Authorization: Bearer <your_jwt_token_here>


**API Documentation**
Interactive API docs are available via Swagger UI at:
http://localhost:8080/swagger-ui/index.html
Use this to explore and test all APIs with live examples.

**Security Details**
Stateless authentication using JWT tokens
Passwords stored securely with BCrypt hashing
Role-based access restrictions enforced via Spring Security
Public endpoints include authentication and Swagger URLs
Custom JWT filter intercepts requests and validates tokens


****Testing****
Use tools like Postman or curl for API testing
Register and login to get JWT token
Pass JWT token in requests to access secured endpoints
Swagger UI can be used for convenient testing

**Future Improvements (Optional)**
Add email notifications and password reset
Implement frontend with React or Angular
Add comprehensive unit and integration tests
Integrate payment gateway for orders
Add Docker support for easy deployment

**Contact**
For questions or support, please contact: [kbc9553@gmail.com]

