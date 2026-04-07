# Secure Digital Wallet Application

### Advanced Java College Project (BCA / MCA / B.Tech CSE)



## Project Overview

The **Secure Digital Wallet Application** is a desktop-based financial management system developed in **Core Java** with a **Swing GUI**. It allows users to register, log in securely, check wallet balance, add money, transfer funds to other registered users, and view a complete transaction history.

The application follows the **MVC (Model-View-Controller)** architecture and implements key **Advanced Java** concepts including **JDBC**, **JavaBeans**, **Object-Oriented Programming (OOP)**, **Exception Handling**, **Multithreading**, and **Basic Security** (SHA-256 hashing).

This project simulates a real-world digital wallet system like **Paytm** or **Google Pay**, demonstrating practical backend database connectivity and secure transaction processing.

---

## Objectives

- Build a fully functional digital wallet with user authentication  
- Implement secure password storage using SHA-256 hashing  
- Ensure atomic transactions (commit/rollback) for money transfers  
- Prevent SQL Injection using PreparedStatement  
- Follow MVC architecture for clean code separation  
- Provide a responsive GUI using Java Swing with multithreading (SwingWorker)  
- Demonstrate custom exception handling for business logic errors  

---

## Features

- **User Registration** – Collects name, email, and password with hashing  
- **Secure Login** – Verifies SHA-256 hashed passwords  
- **Dashboard** – Displays user info and wallet balance  
- **Add Money** – Allows wallet top-up (max ₹1,00,000 per transaction)  
- **Send Money** – Transfer funds securely with validations  
- **Transaction History** – Displays all transactions in JTable  
- **SQL Injection Prevention** – Uses PreparedStatement everywhere  
- **JDBC Transactions** – Commit/rollback for safe transfers  
- **Custom Exception** – InsufficientBalanceException handling  
- **Session Management** – Singleton-based session tracking  
- **Non-blocking GUI** – SwingWorker for smooth UI  

---

## Technologies Used

- **Language:** Java 17+  
- **GUI:** Java Swing  
- **Database:** MySQL 8.x  
- **Connectivity:** JDBC  
- **Driver:** MySQL Connector/J  
- **Architecture:** MVC  
- **Security:** SHA-256  
- **Multithreading:** SwingWorker  
- **Tools:** IntelliJ / Eclipse / NetBeans  

---

## System Architecture

```
VIEW (GUI Layer)
LoginFrame | RegisterFrame | DashboardFrame | SendMoneyFrame
        ↓
CONTROLLER (Service Layer)
AuthenticationService | TransactionService
        ↓
MODEL (Data Layer)
UserBean | TransactionBean
UserDAO | TransactionDAO
        ↓
DATABASE (MySQL)
digital_wallet
```

### Data Flow

1. User interacts with GUI  
2. GUI calls Service layer  
3. Service validates and calls DAO  
4. DAO executes SQL via JDBC  
5. Results return to GUI  

---

## Project Structure

```
DigitalWallet/
│
├── src/com/wallet/
│   ├── main/
│   │   └── WalletApplication.java
│   │
│   ├── gui/frames/
│   │   ├── LoginFrame.java
│   │   ├── RegisterFrame.java
│   │   ├── DashboardFrame.java
│   │   └── SendMoneyFrame.java
│   │
│   ├── beans/
│   │   ├── UserBean.java
│   │   └── TransactionBean.java
│   │
│   ├── dao/
│   │   ├── UserDAO.java
│   │   └── TransactionDAO.java
│   │
│   ├── service/
│   │   ├── AuthenticationService.java
│   │   └── TransactionService.java
│   │
│   ├── util/
│   │   ├── DatabaseUtil.java
│   │   ├── PasswordUtil.java
│   │   └── SessionManager.java
│   │
│   └── exceptions/
│       └── InsufficientBalanceException.java
│
├── resources/database/
│   └── schema.sql
│
├── lib/
│   └── mysql-connector-java-8.x.x.jar
│
└── README.md
```

---

## Advanced Java Concepts Covered

- OOP (Encapsulation, Inheritance, Abstraction)  
- JavaBeans  
- JDBC (PreparedStatement, ResultSet)  
- JDBC Transactions (commit/rollback)  
- SQL Injection Prevention  
- Custom Exceptions  
- Multithreading (SwingWorker)  
- Swing GUI  
- Singleton Pattern  
- SHA-256 Hashing  
- MVC Architecture  

---

## Setup Instructions

### Prerequisites

- Java JDK 17+  
- MySQL Server 8.x  
- MySQL Connector/J  
- IDE (optional)  

---

### Step 1: Create Database

```sql
CREATE DATABASE digital_wallet;
USE digital_wallet;

CREATE TABLE users (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(64),
    balance DECIMAL(12,2) DEFAULT 0.00
);

CREATE TABLE transactions (
    tid INT PRIMARY KEY AUTO_INCREMENT,
    from_user INT,
    to_user INT,
    amount DECIMAL(10,2),
    type ENUM('CREDIT','DEBIT','TRANSFER'),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### Step 2: Configure Database

Update credentials in `DatabaseUtil.java`

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

### Step 3: Add MySQL Driver

Add `mysql-connector-java-8.x.x.jar` to project build path.

---

### Step 4: Run Application

Run `WalletApplication.java`

---

## How to Use

1. Register → Create account  
2. Login → Access dashboard  
3. Add Money → Increase balance  
4. Send Money → Transfer funds  
5. View History → See transactions  
6. Logout → Exit session  

---

## Screenshots

![1](<Screenshot 2026-04-02 222919.png>)
![2](<Screenshot 2026-04-02 223001.png>)
![3](<Screenshot 2026-04-02 222850.png>)
![4](<Screenshot 2026-04-02 222810.png>)
---

## Team Contribution

- **Naina Yadav** – Integration & Security  
- **Bithika Jain** – Transaction Logic  
- **Pradeepti Srivastava** – UI Development  
- **Lavanya Pandit** – Authentication Logic  
- **Dashkrat Srivastava** – Database Layer  

---

## Challenges & Solutions

- Concurrent updates → synchronized methods  
- GUI lag → SwingWorker  
- Security → SHA-256 hashing  
- SQL Injection → PreparedStatement  
- Data consistency → commit/rollback  

---

## Future Enhancements

- 2FA Authentication  
- QR Code Payments  
- Transaction PIN  
- Export to PDF/Excel  
- Password Reset  
- Dark Mode UI  

---

## Conclusion

The Secure Digital Wallet Application demonstrates real-world implementation of Advanced Java concepts including JDBC, Swing, MVC, Multithreading, and Security.

It provides a complete digital wallet system with authentication, transactions, and data integrity, making it a strong academic and practical project.

---

