# Task 4: User CRUD API

## Project Overview

This is a complete Spring Boot REST API for User management. It provides full CRUD (Create, Read, Update, Delete) operations for user entities with proper validation, exception handling, and database integration.

### Features:
- RESTful API endpoints for user management
- Spring Data JPA for database operations
- MySQL database integration
- Input validation using Bean Validation
- Global exception handling
- JSON request/response format

---

## API Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/users` | Create a new user | User object (name, email) | Created user (201) |
| GET | `/users` | Get all users | None | List of users (200) |
| GET | `/users/{id}` | Get user by ID | None | User object (200) |
| PUT | `/users/{id}` | Update user by ID | User object (name, email) | Updated user (200) |
| DELETE | `/users/{id}` | Delete user by ID | None | Success message (200) |

### HTTP Status Codes:
- `200 OK`: Successful operation
- `201 Created`: Resource created successfully
- `400 Bad Request`: Validation error
- `404 Not Found`: Resource not found
- `409 Conflict`: Email already exists
- `500 Internal Server Error`: Server error

---

## Sample JSON Request/Response

### 1. Create User (POST /users)

**Request:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### 2. Get All Users (GET /users)

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com"
  }
]
```

### 3. Get User by ID (GET /users/1)

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Error Response (404 Not Found):**
```json
"Error: User not found with id: 999"
```

### 4. Update User (PUT /users/1)

**Request:**
```json
{
  "name": "John Updated",
  "email": "john.updated@example.com"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com"
}
```

### 5. Delete User (DELETE /users/1)

**Response (200 OK):**
```json
"User deleted successfully"
```

### 6. Validation Error Example

**Request (Invalid email):**
```json
{
  "name": "Test User",
  "email": "invalid-email"
}
```

**Response (400 Bad Request):**
```json
{
  "email": "Email should be valid"
}
```

### 7. Duplicate Email Error

**Response (409 Conflict):**
```json
"Error: Email already exists"
```

---

## Tools Used

### 1. Spring Boot
- **Version**: 3.2.0
- **Purpose**: Framework for building REST APIs
- **Features Used**: Spring Web, Spring Data JPA, Validation

### 2. MySQL
- **Version**: 8.0 or higher
- **Purpose**: Relational database for storing user data
- **Driver**: MySQL Connector/J

### 3. Postman (or similar API testing tool)
- **Purpose**: Testing REST API endpoints
- **Usage**: Send HTTP requests (GET, POST, PUT, DELETE) and verify responses

### 4. Maven
- **Purpose**: Dependency management and project build
- **Configuration**: Defined in `pom.xml`

### 5. Java Development Kit (JDK)
- **Version**: JDK 17 or higher
- **Purpose**: Compile and run Spring Boot application

---

## Setup Instructions

### Prerequisites:
1. JDK 17 or higher installed
2. MySQL server running
3. Maven installed (or use Maven wrapper)

### Steps:

1. **Update Database Configuration**
   - Open `src/main/resources/application.properties`
   - Update database URL, username, and password:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

2. **Create Database**
   ```sql
   CREATE DATABASE your_database_name;
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   Or run `CrudApplication.java` from your IDE

5. **Verify Application is Running**
   - Application starts on `http://localhost:8080`
   - Check logs for "Started CrudApplication"

6. **Test API Endpoints**
   - Use Postman or curl to test endpoints
   - Base URL: `http://localhost:8080/users`

---

## Project Structure

```
Task-4-CRUD-API/
├── src/main/java/com/example/crud/
│   ├── CrudApplication.java          # Main Spring Boot application
│   ├── controller/
│   │   └── UserController.java       # REST controller for user endpoints
│   ├── model/
│   │   └── User.java                 # User entity with validation
│   ├── repository/
│   │   └── UserRepository.java       # JPA repository interface
│   └── exception/
│       └── GlobalExceptionHandler.java # Global exception handler
├── src/main/resources/
│   └── application.properties        # Application configuration
├── pom.xml                           # Maven dependencies
└── README.md                         # This file
```

---

## Validation Rules

The User entity has the following validation constraints:

- **name**: 
  - Required (cannot be blank)
  - Minimum 2 characters
  - Maximum 100 characters

- **email**: 
  - Required (cannot be blank)
  - Must be a valid email format
  - Must be unique in the database

---

## Database Schema

The application automatically creates the `users` table with the following structure:

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
```

---

## Testing with cURL

### Create User:
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'
```

### Get All Users:
```bash
curl http://localhost:8080/users
```

### Get User by ID:
```bash
curl http://localhost:8080/users/1
```

### Update User:
```bash
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Updated","email":"john.updated@example.com"}'
```

### Delete User:
```bash
curl -X DELETE http://localhost:8080/users/1
```

---

## Interview-Safe Design Decisions

1. **Separation of Concerns**: Controller, Service (implicit), Repository layers
2. **Validation**: Bean Validation annotations ensure data integrity
3. **Exception Handling**: Global handler provides consistent error responses
4. **RESTful Design**: Follows REST principles with proper HTTP methods and status codes
5. **Database**: JPA abstracts database operations, making code database-agnostic
6. **Clean Code**: Well-commented, readable, and maintainable code structure

