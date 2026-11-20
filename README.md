# VYCTUS
### Eat Naturally. Live Intelligently.

**VYCTUS** is a personal nutrition and recipe management application designed to help users understand their eating habits, manage recipes, and track nutritional intake.

The project combines food and nutrient information, recipe composition, and daily meal tracking in one application. Its goal is to provide a flexible foundation for understanding not just how much we eat, but also what we eat.

---

## Project Goals

VYCTUS aims to provide:

- **Food Management** – Maintain a structured catalog of foods and their nutritional values.
- **Recipe Management** – Create recipes by combining ingredients and defining quantities.
- **Meal Planning & Tracking** – Record daily meals and their individual components.
- **Nutritional Analysis** – Calculate calories and nutritional values for foods, recipes, and meals.
- **Internationalization** – Support multiple languages and localized reference data.
- **Personalization** – Associate meals, preferences, and other user-specific information with individual users.

## Architecture

VYCTUS follows a **Modular Monolith** architecture.

The application is organized around business domains, with clearly defined responsibilities and separation of concerns. This approach keeps the initial system manageable while allowing individual modules to evolve independently.

The architecture is designed to avoid unnecessary complexity while preserving the possibility of extracting services in the future if business or technical requirements justify it.

### Technology Stack

### Technology Stack

- **Language:** Java
- **Backend Framework:** Spring Boot
- **Persistence:** Spring Data JPA
- **ORM:** Hibernate
- **Database:** PostgreSQL 15.5
- **Database Migration:** Flyway
- **Concurrency Control:** JPA Optimistic Locking (`@Version`)
- **Application Server:** Embedded Apache Tomcat 11
- **Testing:** JUnit 5, Testcontainers

## Database Architecture

VYCTUS uses PostgreSQL schemas to separate business domains and infrastructure concerns.

### Database Schemas

- **auth** – User identity, roles and access management.
- **diary** – Meals, meal types and meal items.
- **localization** – Locales, translations and multilingual reference data.
- **nutrition** – Foods, nutrients and nutritional information.
- **recipe** – Recipes, ingredients and recipe composition.
- **public** – Shared database infrastructure and Flyway metadata.

### Database Design Principles

- **Domain Separation:** Database schemas reflect the application's business domains.
- **Referential Integrity:** Relationships are enforced through database constraints.
- **Schema Evolution:** Flyway manages database changes through versioned migrations.
- **Historical Nutritional Accuracy:** Nutritional information used in recorded meals should remain reproducible even when the underlying food catalog changes.
- **Optimistic Locking:** Version fields help prevent concurrent updates from silently overwriting each other.
- **Internationalization:** User-facing reference data is designed to support multiple locales.

## Development Principles

The project follows these architectural and engineering principles:

- Separation of concerns and modularity.
- Clear boundaries between domain logic and infrastructure.
- Database-first integrity through constraints and relationships.
- Maintainability and testability.
- Incremental development and evolutionary architecture.
- TDD with red-green-refactor cycle.
- Avoiding premature microservice decomposition.

## Project Status

VYCTUS is an evolving personal software project and a practical environment for exploring modern Java enterprise application development.

Development is incremental, with particular attention to domain modeling, database design, persistence strategies, and maintainable application architecture.

Planned functional areas include food catalog management, recipe composition, meal tracking, nutritional calculations, and multilingual support.

## Vision

VYCTUS is more than a calorie calculator. It is an exploration of how structured nutritional data, recipes, and everyday eating habits can be brought together in a coherent and extensible software system.

The long-term vision is to make nutritional awareness more accessible through reliable data, meaningful calculations, and a simple user experience.

---

*VYCTUS – Eat Naturally. Live Intelligently.*# Vyctus
