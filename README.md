# BizTrack POS SaaS (Multi-Tenant)

BizTrack is a **production-oriented Multi-Tenant POS SaaS backend** built using **Spring Boot**.  
This repository currently contains the **initial backend setup**, created with scalability and SaaS architecture in mind.

---

##  Current Progress

The project is in its **initial foundation phase**.  
So far, the following has been completed:

- Spring Boot backend initialized using Spring Initializr
- Maven-based project structure configured
- Application entry point created
- Basic configuration files added
- Project successfully running locally
- Git repository initialized with clean commit history
- GitHub repository connected and pushed

---

## Tech Stack (Current)

- Java 17+
- Spring Boot
- Maven
- Git & GitHub

---

##  Project Structure

```
BizTrack-POS/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com.biztrack.pos
│ │ │ └── BizTrackPosApplication.java
│ │ └── resources/
│ │ └── application.properties
│ └── test/
│ └── BizTrackPosApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

---

## How to Run the Project

### Prerequisites
- Java 17 or above
- Maven
- Git

### Steps
```bash
mvn clean install
mvn spring-boot:run
```
