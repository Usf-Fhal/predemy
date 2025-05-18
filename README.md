# Predemy

## Overview
Predemy is a platform for online education. This project follows an MVP (Minimum Viable Product) approach with microservices architecture.

## Architecture
Project architecture diagram can be found here:
[Architecture Diagram](https://claude.ai/public/artifacts/409a0177-301f-4667-8fe4-ac9a2a8338f0)

## Database Schema
[Database Schema](https://dbdiagram.io/d/predmey-6820d6125b2fc4582f1b5364)

## Services Status

| Service | Status | Description |
|---------|--------|-------------|
| User Service | ✅ MVP Complete | Handles user management, roles, and profiles |
| Course Service | ✅ MVP Complete | Manages courses, enrollments and instructor content |
| Register Service | ✅ Complete | Eureka Server for service discovery |
| Proxy Service | ✅ Complete | API Gateway using Spring Cloud Gateway |
| Auth Service | 🏗️ In Progress | User authentication and authorization |
| Notification Service | 📅 Planned | Email notifications for registered users |

## Project Structure
```
predemy/
├── users/              # User service
├── courses/           # Course management service
├── register/          # Eureka Server
├── proxy/             # API Gateway
├── auth/              # Authentication service
└── notification/      # Notification service (planned)
```

## Technical Stack
- Java 17
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Data JPA
- Maven
- JUnit 5
- H2 Database (Testing)
- PostgreSQL (Production)

## Getting Started
### Prerequisites
- Java 17 or higher
- Maven 3.8+
- PostgreSQL 14+

### Setup and Installation
1. Clone the repository:
```bash
git clone https://github.com/Usf-Fhal/predemy.git
cd predemy
```

2. Build the project:
```bash
mvn clean install
```

## Development Progress
- [x] User Service MVP
  - Basic user management
  - Role-based access control
  - Instructor profiles
- [x] Course Service MVP
  - Course management
  - Enrollment system
  - Instructor content management
- [x] Service Discovery
  - Eureka Server setup
  - Service registration
- [x] API Gateway
  - Spring Cloud Gateway configuration
  - Dynamic route discovery
  - Load balancing
- [ ] Authentication Service
  - JWT implementation
  - Security configuration
- [ ] Notification Service
  - Email notifications
  - User alerts

