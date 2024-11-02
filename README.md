# Pigeon Racing Management System

This project is a **Pigeon Racing Management System** designed to help loft owners and administrators manage pigeons, lofts, and competitions effectively. The system utilizes **Domain-Driven Design (DDD)**, following best practices to ensure a scalable, maintainable, and secure application. 

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Architecture and Technologies](#architecture-and-technologies)
- [Domain Concepts](#domain-concepts)
- [Domain Events](#domain-events)
- [Bounded Contexts](#bounded-contexts)
- [Aggregate Roots](#aggregate-roots)
- [Endpoints](#endpoints)
- [Getting Started](#getting-started)

---

## Project Overview

The Pigeon Racing Management System is built for pigeon racing enthusiasts and administrators to register pigeons, manage lofts, organize competitions, and calculate and view competition results. The system supports role-based access control with **Loft Owners** and **Administrators**, providing tailored functionalities for each role.

## Features

### Pigeon Management
- **Register New Pigeon:** Loft owners can register a pigeon by providing details like leg band number, gender, age, and color.
- **View and Update Pigeon Details:** Loft owners can view a list of pigeons in their loft, update their details, or delete them if necessary.

### Loft Management
- **Register New Loft:** Loft owners can register a loft with unique details including GPS coordinates.
- **Manage Lofts:** Administrators can view, update, or delete lofts as needed.

### Competition Management
- **Create Competition:** Administrators can create new competitions with a name, GPS release point, date, and distance.
- **Assign Pigeons to Competitions:** Administrators can add pigeons to competitions by leg band number. Loft owners can view and confirm the participation of their pigeons.
- **Close Competition:** Competitions can be closed after pigeons have returned to their lofts.

### Competition Results
- **Upload Results:** Administrators can upload arrival times and leg band numbers for each pigeon in a competition.
- **Calculate and View Results:** The system calculates distance, speed, and points for each pigeon. Results are accessible to loft owners and administrators.
- **Export Results:** Results can be exported as PDFs for external use.

### Authentication and Authorization
- **User Registration and Login:** Loft owners and administrators can securely register and log in.
- **Role-Based Access Control:** Access is tailored based on user roles for secure and organized functionality.

---

## Architecture and Technologies

The application adheres to **Domain-Driven Design (DDD)** principles and implements:

- **Spring Data MongoDB**: For seamless data persistence in a NoSQL database.
- **Spring IoC**: For dependency injection and management of the application’s components.
- **Spring MVC**: To create RESTful web services.
- **Spring REST Controller Advice**: For consistent exception handling across the application.
- **JUnit and Mockito**: For writing unit tests and mocking dependencies.
- **MapStruct**: For mapping between domain entities and DTOs


### Key Concepts
- **RESTful API**: All interactions are based on REST APIs.
- **Exception Handling**: Custom exception handling using `@RestControllerAdvice`.
- **Security**: Role-based access and data protection with Spring Security.
- **Responsive Design**: Accessible on both mobile and desktop.

---

## Domain Concepts

The main domain concepts in this system include:

- **Pigeon**
    - Attributes: `Pigeon ID`, `Leg Band Number`, `Gender`, `Age`, `Color`, `Image (optional)`.
- **Loft**
    - Attributes: `Loft ID`, `Name`, `Owner`, `GPS Coordinates`.
- **Competition**
    - Attributes: `Competition ID`, `Name`, `Release Point GPS Coordinates`, `Date and Time`, `Distance`.
- **Competition Result**
    - Attributes: `Competition Result ID`, `Pigeon ID`, `Arrival Time`, `Distance Traveled`, `Flight Speed`, `Competition Points`.

---

## Domain Events

The system’s domain events include:

- **PigeonRegisteredEvent**
- **CompetitionCreatedEvent**
- **PigeonAddedToCompetitionEvent**
- **CompetitionClosedEvent**
- **CompetitionResultsCalculatedEvent**
- **CompetitionResultsViewedEvent**

---

## Bounded Contexts

- **Pigeon Management**: Handles all operations related to pigeons.
- **Loft Management**: Manages lofts and associated details.
- **Competition Management**: Manages creation, assignment, and closure of competitions.
- **Results Management**: Handles result calculation, storage, and display.

---

## Aggregate Roots

- **Pigeon**
- **Loft**
- **Competition**
- **Result**

---

## Endpoints

Below are some key REST endpoints for each module:

### Authentication and Authorization
- **POST** `/api/auth/register`: Register a new user.
- **POST** `/api/auth/login`: User login.

### Pigeon Management
- **POST** `/api/pigeons`: Register a new pigeon.
- **GET** `/api/pigeons`: List pigeons in a loft.
- **PUT** `/api/pigeons/{pigeonId}`: Update pigeon details.
- **DELETE** `/api/pigeons/{pigeonId}`: Delete a pigeon.

### Loft Management
- **POST** `/api/lofts`: Register a new loft.
- **GET** `/api/lofts`: List all lofts (admin).
- **PUT** `/api/lofts/{loftId}`: Update loft details (admin).
- **DELETE** `/api/lofts/{loftId}`: Delete a loft (admin).

### Competition Management
- **POST** `/api/competitions`: Create a new competition (admin).
- **POST** `/api/competitions/{competitionId}/pigeons`: Add pigeons to competition (admin).
- **PUT** `/api/competitions/{competitionId}/close`: Close a competition (admin).

### Results Management
- **POST** `/api/results/upload`: Upload competition data (admin).
- **GET** `/api/results/{competitionId}`: View competition results.
- **GET** `/api/results/{competitionId}/export`: Export results as PDF.

---

## Getting Started

### Prerequisites

- Java 21
- MongoDB
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/pigeon-management-system.git
   cd pigeon-management-system
   ```

2. Set up MongoDB and configure `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/pigeon_db
   ```

3. Install dependencies and run the application:
   ```bash
   mvn install
   mvn spring-boot:run
   ```

4. Access the API documentation:
   Visit `http://localhost:8080/swagger-ui/` for API documentation and testing.

---

## Additional Information

This system is designed with growth and flexibility in mind, ensuring clean code and modular components for easy maintenance. It leverages **Spring IoC** for dependency management, with each feature following **RESTful principles** for scalable and efficient interactions.

For further questions or contributions, feel free to open an issue or submit a pull request.

--- 

