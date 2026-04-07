# 🛡️ Secure Digital Wallet Application

A robust, desktop-based financial application built using **Core Java** and **Swing**. This project demonstrates the implementation of **MVC (Model-View-Controller)** architecture, secure data handling, and atomic database transactions for a reliable user experience.
---## 🚀 Key Features
*   **🔐 Secure Authentication:** Password hashing using **SHA-256** and SQL injection prevention via `PreparedStatement`.*   **💸 Fund Transfers:** Atomic peer-to-peer transfers using **JDBC Transactions** (Commit/Rollback) to ensure data integrity.
*   **📊 Interactive Dashboard:** Real-time balance updates and a detailed `JTable` transaction log.
*   **⚠️ Custom Error Handling:** Robust business logic validation with custom exceptions like `InsufficientBalanceException`.*   **🛠️ Session Management:** In-memory tracking of logged-in users for secure navigation between frames.
---## 🏗️ Architecture Overview
The project follows a strict **MVC Pattern** to separate concerns:


| Layer | Responsibility | Components |
|---|---|---|
| **View** | Presentation & UI | `LoginFrame`, `DashboardFrame`, `SendMoneyFrame` |
| **Controller** | Business Logic & Flow | `AuthenticationService`, `TransactionService` |
| **Model** | Data Objects & DB Access | `UserBean`, `TransactionBean`, `UserDAO`, `TransactionDAO` |
| **Utilities** | Helper Functions | `DatabaseUtil`, `PasswordUtil`, `SessionManager` |
---## 🛠️ Tech Stack- **Runtime:** Java JDK 17+- **Frontend:** Java Swing (Desktop GUI)- **Database:** MySQL 8.x- **Persistence:** JDBC with Connection Pooling
- **Security:** `java.security.MessageDigest` (SHA-256)
---## ⚙️ Setup & Installation### 1. Database InitializationExecute the provided SQL script in your MySQL environment:
```bash
mysql -u root -p < resources/database/schema.sql

## 2. Configuration
Update your credentials in src/com/wallet/util/DatabaseUtil.java:

private static final String URL      = "jdbc:mysql://localhost:3306/digital_wallet";private static final String USER     = "your_username";private static final String PASSWORD = "your_password";

## 3. Build and Run

* IDE: Add mysql-connector-java-8.x.x.jar to your project libraries.
* Run: Execute the WalletApplication.java file.

------------------------------
## 📂 Directory Structure

DigitalWallet/
├── src/com/wallet/
│   ├── main/          # Entry Point
│   ├── gui/           # View: Swing UI Components
│   ├── service/       # Controller: Business Logic
│   ├── dao/           # Model: Data Access Objects
│   ├── beans/         # Model: Plain Old Java Objects (POJOs)
│   ├── util/          # Helpers: JDBC, Hashing, Sessions
│   └── exceptions/    # Custom Project Exceptions
└── resources/         # SQL Schemas & External Assets

------------------------------
## 👥 Project Contributors

| Name | Role | GitHub |
| Pradeepti Srivastava | UI & Frame Design
| Naina Yadav | Integration & Security
| Bithika Jain | Business Logic & Exceptions
| Lavanya Pandit | Authentication Layer
| Dashkrat Srivastava | Database Engineering

