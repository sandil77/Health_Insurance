# Health Insurance Backend (Microservices Architecture)

This repository contains the **backend** implementation of the Health Insurance Project built using **Java Spring Boot** and **Microservices Architecture**.

## 📌 Overview
The backend is composed of multiple microservices:
- Policy Service** – Manages health insurance policies.
- Claim Service** – Handles insurance claims.
- User Service** – Manages user registration, authentication, and profiles.
- API Gateway** – Acts as the single entry point for the frontend.
- Service Registry (Eureka Server)** – Handles service discovery and registration.

---

## 🛠 Tech Stack
- Java 17+
- Spring Boot
- Spring Cloud Netflix Eureka
- Spring Data JPA
- Spring Web
- Spring Boot Actuator
- Spring Cloud Gateway
- MySQL
- Maven

---

## ⚙️ Microservice Communication
1. **Service Registration** – Each microservice registers itself with the Eureka Server.
2. **Service Discovery** – Microservices use Eureka to locate and communicate with each other.
3. **API Gateway Routing** – The API Gateway uses service names from Eureka to route requests.
4. **Load Balancing** – Eureka works with Spring Cloud LoadBalancer to distribute traffic.

---
API Endpoints (via API Gateway)
GET /user-service/users – Get all users

POST /policy-service/policies – Create a new policy

POST /claim-service/claims – Submit a new claim
