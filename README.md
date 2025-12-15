# BizTrack POS SaaS (Multi-Tenant) – Backend

A **production-oriented Spring Boot backend** for a **Multi-Tenant POS (Point of Sale) SaaS application**, built with **Spring Boot + Spring Security + JWT authentication**.

This repository is designed in a **beginner-friendly way**, but follows **real industry practices** so that learners can understand **how real backend systems are built**.

---

##  Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven
- MySQL / PostgreSQL (configurable)
- Git & GitHub (industry-grade flow)

---

##  Project Structure (Important)
```
src/main/java/com/biztrack/pos
│
├── configuration
│ ├── SecurityConfig.java
│ ├── JwtValidator.java
│ ├── JwtProvider.java
│ └── JwtConstants.java
│
├── BizTrackPosApplication.java
│
src/main/resources
│
├── application.properties
```

---

##  Step-by-Step: What We Have Built So Far

This section explains **each file in simple terms**, so even beginners can follow.

---

##  Spring Boot Project Setup

The project was created using **Spring Initializr** and imported into **IntelliJ IDEA**.

### What this gives us
- A ready-to-run Spring Boot application
- Maven build system
- Clean project structure

Main entry file:
```java
BizTrackPosApplication.java
```
