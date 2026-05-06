# System Checklist

> A Roadmap of architectural and design considerations for the project. This checklist serves as a reminder of the key principles and best practices that guided linearly for organized the development process.

## System Requirements

> This checklist outlines the architectural principles, technology stack, and application requirements for the project.

### Project Setup

- [x] Readme file created with project overview and needed documentation.
- [x] Layered Monolith project structure diagram created to outline the organization of the codebase. and how the different layers (UI, Controller, Service, DAO, Model) interact with each other.
- [x] ER Diagram created to visualize the database schema and relationships between entities.
- [x] Github main protected branch with required PR reviews and status checks.
- [x] GitHub Actions workflow set up for CI with JUnit, AssertJ, Spotless, SpotBugs, and Jacoco.
- [x] Maven wrapper configured for consistent build and dependency management across all environments.
- [x] JavaFX dependencies properly set up in the Maven `pom.xml` file.

### Architectural Principles

- [x] Layered Monolith structure to keep the codebase organized and maintainable.
- [x] Clean Architecture with clear separation of concerns (UI, Controller, Service, DAO, Model; MVC + Service + Dao).

### Technology Stack

- [x] Java 25 LTS + Maven + JavaFX for the application.
- [x] SQLite with JDBC for local database storage + Flyway for database migrations.
- [x] GitHub Flow for version control and collaboration.
- [x] GitHub Actions for CI with JUnit, AssertJ, Spotless, SpotBugs, and Jacoco.
- [x] jpackage for packaging the application for distribution.
- [x] Mermaid diagrams for visual documentation of architecture and design decisions.

### Security and Validation

- [ ] Input Validation: Ensure all user inputs are validated on to prevent invalid data and potential security vulnerabilities such as SQL Injection. Use `prepared statements` for all database interactions.
- [ ] Password Hashing: Use BCrypt to securely `hash and salt` user passwords before storing them in the database.
- [ ] Error Handling: Implement comprehensive `error handling` to gracefully handle exceptions and provide user-friendly error messages.
- [ ] Immutable Data Structures: Use immutable data structures where we don't want other data such as username to be changed after creation, only password can be updated only via `SQL update statement`, not by getter/setter methods.
- [ ] Test Coverage: Aim for high test coverage with unit tests for all service and DAO methods, SQL Injection tests, and integration tests for critical user flows (e.g., registration, login, checkout).

## Application Requirements

> This checklist outlines the minimum viable product (MVP) features and additional optional features for the application.

### MVP - Minimum Viable Product (Required)

- [ ] Session Context: The application should maintain a session context to track the logged-in user and their interactions across different screens.
- [x] Login Screen with username and password fields, and a login button. The login process should validate the credentials against the database and establish a session context upon successful authentication.
- [x] Registration Screen with username, password, confirm password fields, and a register button.
- [ ] Main Screen with a list of items with an add to cart button and a logout button.
- [ ] Cart Screen with a list of items that have been added to the cart, quantity selectors, and a checkout button.
- [ ] Checkout Screen with a summary of the order, payment options, and a confirm button.
- [ ] Design: A visually appealing and user-friendly interface with consistent styling and responsive design.

### Extra Features (Optional)

- [ ] Promotions/Sale/Discounts: A seasonal promotion system that applies discounts to certain products or categories during checkout.
- [ ] Promotion Codes: Allow users to enter promo codes for discounts during checkout.
- [ ] Product Search: A search bar on the main screen to filter items by name or category.
- [ ] Product Categories: Organize items into categories (e.g., Electronics, Clothing) for easier browsing.
- [ ] Product images: Display images for each product in the product list and product details view.
- [ ] Product Views: A screen when tapped, shows where users can view detailed information about a product, including images, descriptions, and reviews.
- [ ] Product Reviews: Allow users to leave reviews and ratings for products they have purchased.
- [ ] Payment methods: Support fake multiple payment options eg. Credit Card, Cash on Delivery, GCash, etc. (no real payment processing needed).
- [ ] Order History: A screen where users can view their past orders and details.
- [ ] User Profile: A screen where users can view and edit their profile information, such as email and password.
