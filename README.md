# Predemy

## Overview
Predemy is a platform for online education. This project follows an MVP (Minimum Viable Product) approach with microservices architecture.

## Security Architecture
We implement a hybrid security approach for optimal performance and security:

- **Standard Requests**: JWT stateless validation
  - Signature verification
  - Expiration check
  - Role-based access control

- **Critical Operations** (Planned): Enhanced security with state validation
  - JWT validation plus database check
  - Redis-based token blacklist/verification
  - Will be used for sensitive operations:
    - Password changes
    - Payment processing
    - Account deletion
    - Profile updates

## Services Status

| Service | Status | Description |
|---------|--------|-------------|
| User Service | ✅ MVP Complete | Handles user management, roles, and profiles |
| Course Service | ✅ MVP Complete | Manages courses, enrollments and instructor content |
| Register Service | ✅ Complete | Eureka Server for service discovery |
| Proxy Service | ✅ Complete | API Gateway using Spring Cloud Gateway |
| Auth Service | 🚧 In Development | Basic JWT authentication, more features planned |
| Notification Service | 📅 Planned | Email notifications for registered users |

## Auth Service Roadmap
- [x] Basic JWT authentication
- [x] Role-based access control
- [x] Service-to-service communication
- [ ] Refresh token implementation
- [ ] Redis integration for token management
- [ ] Enhanced security for sensitive operations
- [ ] Token blacklisting
- [ ] Session management
- [ ] Rate limiting

## Technical Stack
- Java 17
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Data JPA
- Redis (Planned - Security state management)
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
  - Hybrid JWT implementation
    - Stateless validation for standard requests
    - Redis-backed validation for critical operations
  - Security configuration
  - Token management

