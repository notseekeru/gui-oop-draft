# Architecture Diagrams

> This document contains the architectural diagrams of the project. It is intended to provide a visual representation of the architecture and design of the project. It is not meant to be a detailed technical document, but rather a high-level overview of the architecture and design decisions made during the development of the project.
> The diagrams are created using Mermaid, a simple markdown-like syntax for generating diagrams and flowcharts. The diagrams are included in the documentation to help developers understand the architecture and design of the project, and to provide a reference for future development and maintenance.

## Mermaid Diagrams

1. Simple Application Flow
   - This diagram illustrates the flow of the application, showing how the different layers (UI, Controller, Service, DAO, Model) interact with each other and with the database.

```mermaid
---
config:
  layout: elk
---
flowchart LR
    LoginScreen["Login Screen (UI)"] --> LoginController["LoginController"]
    LoginController --> AuthService["LoginService"]
    AuthService --> UserDAO["UserDAO"] & SessionContext(("Session Context"))
    UserDAO --> Database[("SQLite DB")]

     LoginScreen:::ui
     LoginController:::logic
     AuthService:::logic
     UserDAO:::data
     Database:::data
     SessionContext:::context
    classDef ui stroke:#818cf8,fill:#eef2ff
    classDef logic stroke:#2dd4bf,fill:#f0fdfa
    classDef data stroke:#a78bfa,fill:#f5f3ff
    classDef context stroke:#fb923c,fill:#fff7ed
```

2. Checklist Mind Map
   - This diagram represents the checklist of architectural and design considerations that were taken into account during the development of the project. It serves as a reminder of the key principles and best practices that guided the architectural decisions.

```mermaid
mindmap
  root(MAIN)
    System Requirements
      Project Setup
        Readme file
        Monolith structure diagram
        ER Diagram
        Github main branch protection
        GitHub Actions CI
        Maven wrapper
        JavaFX in pom.xml
      Architectural Principles
        Layered Monolith
        Clean Architecture
      Technology Stack
        Java 25 LTS + Maven + JavaFX
        SQLite + JDBC + Flyway
        GitHub Flow
        GitHub Actions
        jpackage
    Application Requirements
      MVP
        Session Context
        Login Screen
        Registration Screen
        Main Screen
        Cart Screen
        Checkout Screen
        Design
      Extra Features
        Promotions/Sale/Discounts
        Promotion Codes
        Product Search
        Product Categories
        Product Images
        Product Views
        Product Reviews
        Payment Methods
        Order History
        User Profile
      Security and Validation
        Input Validation
        Password Hashing + Salting
        Error Handling
        Immutable Data Structures
        Test Coverage
```

3. ERD v1
   - This diagram shows the Entity-Relationship Diagram (ERD) of the database schema, illustrating the tables, their attributes, and the relationships between them.

```mermaid
erDiagram
    users {
        integer user_id PK "User ID"
        varchar name "User Name"
        varchar email "User Email"
        varchar password "User Password"
    }
    categories {
        integer category_id PK "Category ID"
        varchar category_name "Category Name"
    }
    products {
        integer product_id PK "Product ID"
        varchar name "Product Name"
        varchar description "Product Description"
        integer price "Product Price"
        integer stock_quantity "Stock Quantity"
        integer category_id FK "Category ID"
    }
    cart_items {
        integer item_id PK "Cart Item ID"
        integer user_id FK "User ID"
        integer product_id FK "Product ID"
        integer quantity "Quantity"
    }
    users ||--o{ cart_items : contains
    products ||--o{ cart_items : contains
    categories ||--o{ products : contains
```

4. Mermaid GHA Workflow Diagram
   - This diagram represents the GitHub Actions workflow for the CI/CD pipeline, showing the different jobs and steps that are executed when code is pushed to the repository.

Refer to .github/workflows/ci.yml for the actual workflow file.

```mermaid
---
config:
  layout: fixed
---
flowchart LR
    A["Start: Trigger"] --> B["Setup Node"]
    B --> C["Pull Docker"]
    C --> D{"Files found?"}
    D -- No --> E["Skip"]
    D -- Yes --> G1["Gen PNGs"]
    E --> H["End"]
    G1 --> G2["Clean"]
    G2 --> I{"Same repo?"}
    I -- No --> H
    I -- Yes --> J["Commit/Push"]
    J --> K["Resolve"]
    K --> H
    n1["WORK FLOW OF MERMAID GITHUB ACTIONS YML"]

    n1@{ shape: text}
     A:::process
     B:::process
     C:::process
     D:::decision
     E:::process
     G1:::process
     H:::endNode
     G2:::process
     I:::decision
     J:::process
     K:::process
    classDef process stroke:#818cf8,fill:#eef2ff
    classDef decision stroke:#fb923c,fill:#fff7ed
    classDef endNode stroke:#f87171,fill:#fef2f2
    style A stroke:#00C853
```
