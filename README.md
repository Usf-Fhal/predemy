# Predemy

## Overview
Predemy is a platform for online education. This project follows an MVP (Minimum Viable Product) approach with microservices architecture.

## Architecture
Project architecture diagram can be found here:
[Architecture Diagram](https://claude.ai/public/artifacts/409a0177-301f-4667-8fe4-ac9a2a8338f0)

## Services Status

| Service | Status | Description |
|---------|--------|-------------|
| User Service | ✅ MVP Complete | Handles user management, roles, and profiles |
| Auth Service | 🏗️ In Progress | User authentication and authorization |
| Notification Service | 📅 Planned | Email notifications for registered users |

## Technical Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- JUnit 5
- H2 Database (Testing)
- PostgreSQL (Production)

## Project Structure
```
predemy/
├── users/              # User service
├── auth/               # Authentication service
└── notification/       # Notification service (planned)
```

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
- [ ] Authentication Service
  - JWT implementation
  - Security configuration
- [ ] Notification Service
  - Email notifications
  - User alerts

