# Secure Digital Wallet Application

**Advanced Java Programming | CSE4019**
**VIT Bhopal University – School of Computing Science and Engineering**
**| Winter Semester (Session 2025-26)**

---

## Project Details

* **Project Title:** Secure Digital Wallet Application
* **Course:** Advanced Java Programming (CSE4019)
* **Slot:** B14 + B23
* **Class No.:** BL2025260500381
* **Academic Year:** 2025–2026

---

## 1. Project Description

The **Secure Digital Wallet Application** is a desktop-based system developed using Core Java and Java Swing for the graphical user interface. It enables users to perform essential digital wallet operations such as account creation, authentication, balance management, and secure money transactions.

The system ensures data integrity and security by implementing password hashing (SHA-256), secure database interaction using JDBC, and structured exception handling. The application follows the **MVC (Model–View–Controller)** design pattern, ensuring separation of concerns, better maintainability, and scalability.

---

## 2. Features

| Feature                  | Details                                                                                      |
| ------------------------ | -------------------------------------------------------------------------------------------- |
| User Registration        | Allows new users to create an account using name, email, and password with proper validation |
| Secure Login             | Passwords are encrypted using SHA-256 before storage, ensuring user data security            |
| Wallet Balance           | Displays real-time wallet balance on the dashboard after login                               |
| Add Money                | Users can add funds with a maximum limit of ₹1,00,000 per transaction                        |
| Send Money               | Enables secure transfer of funds to other registered users via email                         |
| Transaction History      | Complete transaction records displayed using JTable for better readability                   |
| SQL Injection Prevention | All database queries use PreparedStatement to prevent SQL injection                          |
| JDBC Transactions        | Ensures atomic operations during money transfer using commit and rollback                    |
| Custom Exception         | Uses InsufficientBalanceException to handle insufficient balance scenarios                   |
| Session Management       | Tracks logged-in users using an in-memory SessionManager                                     |

---

## 3. Tech Stack

| Component    | Technology                          |
| ------------ | ----------------------------------- |
| Language     | Java 17+                            |
| GUI          | Java Swing                          |
| Database     | MySQL 8.x                           |
| JDBC Driver  | mysql-connector-java                |
| Architecture | MVC (Model–View–Controller)         |
| Security     | SHA-256 hashing using MessageDigest |
| Build Tool   | javac / IntelliJ / Eclipse          |

---

## 4. Project Structure

```text
DigitalWallet/
├── src/com/wallet/
│   ├── main/
│   │   └── WalletApplication.java          ← Entry point of application
│   ├── gui/frames/
│   │   ├── LoginFrame.java                 ← Handles user login interface
│   │   ├── RegisterFrame.java              ← Handles user registration interface
│   │   ├── DashboardFrame.java             ← Displays wallet dashboard and balance
│   │   └── SendMoneyFrame.java             ← Manages fund transfer operations
│   ├── beans/
│   │   ├── UserBean.java                   ← Represents user data model
│   │   └── TransactionBean.java            ← Represents transaction data model
│   ├── dao/
│   │   ├── UserDAO.java                    ← Handles database operations for users
│   │   └── TransactionDAO.java             ← Handles database operations for transactions
│   ├── service/
│   │   ├── AuthenticationService.java      ← Contains login and registration logic
│   │   └── TransactionService.java         ← Handles wallet business logic
│   ├── util/
│   │   ├── DatabaseUtil.java               ← Manages database connection (JDBC)
│   │   ├── PasswordUtil.java               ← Handles password hashing (SHA-256)
│   │   └── SessionManager.java             ← Maintains active user session
│   └── exceptions/
│       └── InsufficientBalanceException.java ← Custom exception for insufficient funds
├── resources/database/
│   └── schema.sql                          ← MySQL database schema
└── README.md
```

---

## 5. Setup Instructions

### Step 1: Install Prerequisites

Ensure the following are installed:

* Java JDK 17 or higher
* MySQL Server 8.x
* MySQL Connector/J (JDBC driver)

---

### Step 2: Set Up Database

Run the following command in MySQL Workbench or terminal:

```sql
source resources/database/schema.sql;
```

This will create the required database and tables for the application.

---

### Step 3: Configure Database Credentials

Open the file:

```
src/com/wallet/util/DatabaseUtil.java
```

Update credentials:

```java
private static final String USER = "root";
private static final String PASSWORD = "";
```

---

### Step 4: Add MySQL Driver

Add the MySQL Connector JAR to your project:

* IntelliJ: Project Structure → Libraries → Add JAR
* Eclipse: Build Path → Add External JAR

---

### Step 5: Run the Application

Execute the main class:

```
WalletApplication.java
```

The application will launch with the login interface.

---

## 6. Team Contribution

| Member                   | Role                        | Files                                                             |
| ------------------------ | --------------------------- | ----------------------------------------------------------------- |
| **Pradeepti Srivastava**    |  **UI Developer**  |LoginFrame, RegisterFrame, DashboardFrame, SendMoneyFrame                                |
| Naina Yadav                 |   Integration & Security              |WalletApplication, SessionManager, DatabaseUtil, PasswordUtil, schema.sql        |
| Bithika Jain                |  Transaction & Wallet Logic  | TransactionBean, TransactionService, InsufficientBalanceException    |
| Lavanya Pandit              |  Authentication & User Logic |  UserBean, AuthenticationService  |
| Dashkrat Srivastava         |  Database Layer     | UserDAO, TransactionDAO      |

---

## 7. Advanced Java Topics Covered

* Object-Oriented Programming (Encapsulation, Abstraction, Inheritance)
* JavaBeans with standard getters and setters
* JDBC (Connection handling, PreparedStatement, ResultSet, Transactions)
* Java Swing (JFrame, JPanel, JTable, JButton for UI design)
* Exception Handling (Custom and built-in exceptions)
* Security (SHA-256 hashing, SQL injection prevention)
* MVC Architecture (Separation of View, Controller, Model layers)
* Multi-threading using SwingWorker for responsive UI

---
