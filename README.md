# BizTrack-POS Authentication System

A secure JWT-based authentication system built with Spring Boot for the BizTrack Point of Sale application.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [API Documentation](#api-documentation)
- [Testing with Postman](#testing-with-postman)
- [Project Structure](#project-structure)
- [Security Features](#security-features)

---

##  Features

-  User Registration (Signup)
-  User Login with JWT Token
-  Password Encryption (BCrypt)
-  Role-Based Access Control (5 roles)
-  JWT Token Validation
-  Duplicate Email Prevention
-  CORS Configuration for Frontend
-  MySQL Database Integration

---

##  Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 4.0.0 | Backend Framework |
| Spring Security | 4.0.0 | Authentication & Authorization |
| Spring Data JPA | 4.0.0 | Database ORM |
| MySQL | 8.0+ | Database |
| JWT (JJWT) | 0.12.6 | Token Generation |
| Lombok | 1.18.42 | Reduce Boilerplate Code |
| Maven | 3.8+ | Build Tool |

---

##  Prerequisites

Before running this project, make sure you have:

-  Java 17 or higher installed
-  MySQL Server running (8.0+)
-  Maven installed
-  IDE (IntelliJ IDEA / Eclipse / VS Code)
-  Postman (for API testing)

---

##  Installation

### 1. Clone the Repository
```bash
git clone https://github.com/aloknaok01/BizTrack-POS.git
cd BizTrack-POS
```

### 2. Configure Database
Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/BizTrackPOS
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Create Database
```sql
CREATE DATABASE BizTrackPOS;
```

### 4. Install Dependencies
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

Application will start on: **http://localhost:5000**

---

##  Database Setup

### Tables Created Automatically

The application uses **Hibernate DDL auto-update** to create tables automatically.

**User Table Schema:**
```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    role VARCHAR(50) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    last_login DATETIME
);
```

### User Roles

| Role | Description |
|------|-------------|
| `ROLE_ADMIN` | Super administrator (cannot signup via API) |
| `ROLE_USER` | Regular user |
| `ROLE_CASHIER` | Store cashier |
| `ROLE_BRANCH_MANAGER` | Branch manager |
| `ROLE_STORE_MANAGER` | Store manager |

---

##  API Documentation

### Base URL
```
http://localhost:5000
```

---

### 1 - User Signup

**Endpoint:** `POST /auth/signup`

**Description:** Register a new user account

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "password": "securePassword123",
  "role": "ROLE_STORE_MANAGER"
}
```

**Success Response (200 OK):**
```json
{
  "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImpvaG5AZXhhbXBsZS5jb20iLCJhdXRob3JpdGllcyI6IlJPTEVfU1RPUkVfTUFOQUdFUiIsImlhdCI6MTcwMzE3MjgwMCwiZXhwIjoxNzAzMjU5MjAwfQ.xyz123",
  "message": "Registered Successfully!",
  "user": {
    "id": 1,
    "fullName": "Your Name",
    "email": "yourname@example.com",
    "phone": "1234567890",
    "role": "ROLE_STORE_MANAGER",
    "createdAt": "2025-12-21T23:30:00",
    "updatedAt": "2025-12-21T23:30:00",
    "lastLogin": "2025-12-21T23:30:00"
  }
}
```

**Error Responses:**

| Status Code | Error | Description |
|------------|-------|-------------|
| 400 | Email id already registered | Email already exists in database |
| 400 | Role admin is not allowed | Cannot signup with ROLE_ADMIN |
| 400 | JSON parse error | Invalid request format |

---

### 2- User Login

**Endpoint:** `POST /auth/login`

**Description:** Login with existing credentials

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "email": "yourname@example.com",
  "password": "securePassword123"
}
```

**Success Response (200 OK):**
```json
{
  "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImpvaG5AZXhhbXBsZS5jb20iLCJhdXRob3JpdGllcyI6IlJPTEVfU1RPUkVfTUFOQUdFUiIsImlhdCI6MTcwMzE3MjgwMCwiZXhwIjoxNzAzMjU5MjAwfQ.xyz123",
  "message": "Logged Successfully!",
  "user": {
    "id": 1,
    "fullName": "Your Name",
    "email": "yourname@example.com",
    "phone": "1234567890",
    "role": "ROLE_STORE_MANAGER",
    "createdAt": "2025-12-21T23:30:00",
    "updatedAt": "2025-12-21T23:30:00",
    "lastLogin": "2025-12-21T23:45:00"
  }
}
```

**Error Responses:**

| Status Code | Error | Description |
|------------|-------|-------------|
| 401 | Email does not exist | User not found in database |
| 401 | Password does not match | Incorrect password |

---

##  Testing with Postman

### 1. Import Collection

Create a new Postman collection with these requests:

### 2. Test Signup

```http
POST http://localhost:5000/auth/signup
Content-Type: application/json

{
  "fullName": "Test User",
  "email": "test@example.com",
  "phone": "9876543210",
  "password": "test12345",
  "role": "ROLE_CASHIER"
}
```

### 3. Test Login

```http
POST http://localhost:5000/auth/login
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "test12345"
}
```

### 4. Test Protected Endpoint (Future)

```http
GET http://localhost:5000/api/products
Authorization: Bearer YOUR_JWT_TOKEN_HERE
```

---

##  Project Structure

```
BizTrack-POS/
│
├── src/main/java/com/biztrack/pos/
│   ├── configuration/          # Security & JWT Configuration
│   │   ├── JwtConstants.java
│   │   ├── JwtProvider.java
│   │   ├── JwtValidator.java
│   │   └── SecurityConfig.java
│   │
│   ├── controller/             # REST API Controllers
│   │   └── AuthController.java
│   │
│   ├── domain/                 # Enums
│   │   └── UserRole.java
│   │
│   ├── exceptions/             # Custom Exceptions
│   │   └── UserException.java
│   │
│   ├── mapper/                 # DTO Mappers
│   │   └── UserMapper.java
│   │
│   ├── models/                 # Database Entities
│   │   └── User.java
│   │
│   ├── payload/                # Request/Response DTOs
│   │   ├── dto/
│   │   │   └── UserDto.java
│   │   └── response/
│   │       └── AuthResponse.java
│   │
│   ├── repository/             # Database Repositories
│   │   └── UserRepository.java
│   │
│   ├── service/                # Business Logic
│   │   ├── AuthService.java
│   │   └── impl/
│   │       ├── AuthServiceImpl.java
│   │       └── CustomUserImplementation.java
│   │
│   └── BizTrackPosApplication.java  # Main Application
│
├── src/main/resources/
│   └── application.properties  # Configuration
│
└── pom.xml                     # Maven Dependencies
```

---

##  Security Features

### 1. Password Encryption
- Uses **BCrypt** algorithm
- Passwords are hashed before storing in database
- Original password cannot be retrieved

### 2. JWT Token
- **Token Expiration:** 24 hours (86400000 milliseconds)
- **Algorithm:** HS256 (HMAC with SHA-256)
- **Claims:** Email, Authorities (roles)
- **Header:** `Authorization: Bearer <token>`

### 3. CORS Configuration
Allowed origins:
- `http://localhost:3000` (React default)
- `http://localhost:5173` (Vite default)

### 4. Endpoint Protection

| Endpoint | Access Level |
|----------|--------------|
| `/auth/**` | Public (No authentication required) |
| `/api/**` | Protected (JWT token required) |
| `/api/super-admin/**` | Admin only (ROLE_ADMIN required) |

---

##  Authentication Flow

### Signup Flow
```
1. User submits signup form
2. Backend validates email uniqueness
3. Backend encrypts password (BCrypt)
4. User saved to database
5. JWT token generated
6. Token + User data returned to frontend
7. Frontend stores token in localStorage
```

### Login Flow
```
1. User submits login credentials
2. Backend finds user by email
3. Backend compares passwords (BCrypt)
4. If valid, generate JWT token
5. Update lastLogin timestamp
6. Return token + user data
7. Frontend stores token
```

### Protected Request Flow
```
1. Frontend includes JWT in Authorization header
2. JwtValidator intercepts request
3. Token validated and decoded
4. User authenticated in SecurityContext
5. Request proceeds to controller
6. Response returned
```

---

##  Common Issues & Solutions

### Issue 1: Database Connection Failed
**Error:** `Connection refused`

**Solution:**
- Make sure MySQL server is running
- Verify database credentials in `application.properties`
- Check if port 3306 is available

### Issue 2: Table doesn't exist
**Error:** `Table 'BizTrackPOS.user' doesn't exist`

**Solution:**
- Set `spring.jpa.hibernate.ddl-auto=update` in properties
- Restart application to auto-create tables

### Issue 3: JWT Token Invalid
**Error:** `BadCredentialsException: invalid credentials`

**Solution:**
- Check if token is expired (24 hours validity)
- Verify token format: `Bearer <token>`
- Ensure JWT_SECRET matches between token generation and validation

### Issue 4: CORS Error
**Error:** `CORS policy: No 'Access-Control-Allow-Origin' header`

**Solution:**
- Add your frontend URL to `SecurityConfig.corsConfigurationSource()`
- Restart the application

---

##  Environment Variables (Optional)

For production, use environment variables instead of hardcoding:

```properties
# Database
DB_URL=${DATABASE_URL}
DB_USERNAME=${DATABASE_USERNAME}
DB_PASSWORD=${DATABASE_PASSWORD}

# JWT
JWT_SECRET=${JWT_SECRET_KEY}
JWT_EXPIRATION=${JWT_EXPIRATION_TIME}

# Server
SERVER_PORT=${PORT:5000}
```

---

##  API Response Format

### Success Response
```json
{
  "jwt": "string",
  "message": "string",
  "user": {
    "id": "number",
    "fullName": "string",
    "email": "string",
    "phone": "string",
    "role": "string",
    "createdAt": "datetime",
    "updatedAt": "datetime",
    "lastLogin": "datetime"
  }
}
```

### Error Response
```json
{
  "timestamp": "datetime",
  "status": "number",
  "error": "string",
  "message": "string",
  "path": "string"
}
```

---

##  Database Sample Data

After successful signup, your database will look like:

```sql
SELECT * FROM user;
```

| id | full_name | email | password | phone | role | created_at | updated_at | last_login |
|----|-----------|-------|----------|-------|------|------------|------------|------------|
| 1 | John Doe | john@example.com | $2a$10$... | 1234567890 | ROLE_STORE_MANAGER | 2025-12-21 23:30:00 | 2025-12-21 23:30:00 | 2025-12-21 23:30:00 |

---
##  Roadmap (Future Features)

- [ ] Email verification
- [ ] Password reset functionality
- [ ] Refresh token mechanism
- [ ] OAuth2 integration (Google, Facebook)
- [ ] Two-factor authentication (2FA)
- [ ] Account lockout after failed attempts
- [ ] Password strength validation
- [ ] User profile management

---

##  Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

##  License

This project is licensed under the MIT License - see the LICENSE file for details.

---

##  Author

**Alok kumar Naik**
- GitHub: [@aloknaok01](https://github.com/aloknaok01)
- Email: aloknaik016261@.com

---

##  Acknowledgments

- Spring Boot Documentation
- JWT.io
- Stack Overflow Community
- Baeldung Tutorials

---

## Support

For issues and questions:
- Open an issue on GitHub
- Email: support@biztrack.com

**Made with ❤️ for BizTrack POS System by Alok**
