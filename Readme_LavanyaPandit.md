# 🔐 Secure Digital Wallet Application

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-00758F?logo=mysql)
![Architecture](https://img.shields.io/badge/Architecture-MVC-green)
![Security](https://img.shields.io/badge/Security-SHA--256-red)
![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen)

A **production-grade desktop Secure Digital Wallet** built using **Core Java (17+)** and **Swing GUI**, following a strict **MVC architecture**.

This application ensures **secure financial transactions**, **robust session management**, and **enterprise-level data protection**, making it suitable for real-world financial system simulations.

---

## ✨ Features

* 🔐 **Secure Authentication**

  * SHA-256 password hashing with salting
  * Email validation & duplicate prevention

* 👤 **Session Management**

  * Token-based sessions
  * Auto-expiry & secure logout
  * Prevents concurrent logins

* 💰 **Wallet System**

  * Real-time balance updates
  * ₹ currency formatting
  * Add money with limits (₹10 – ₹1,00,000)

* 🔄 **Money Transfer**

  * Peer-to-peer transfer via email
  * Atomic transactions (commit/rollback)

* 📊 **Transaction History**

  * Searchable audit logs
  * Filters by date/type/amount
  * Export-ready table

* 🛡 **Security & Reliability**

  * 100% SQL injection protection (PreparedStatement)
  * ACID-compliant transactions
  * Custom exception handling

---

## 🧰 Tech Stack

| Layer        | Technology                  |
| ------------ | --------------------------- |
| Language     | Java (OpenJDK 17+)          |
| GUI          | Java Swing                  |
| Database     | MySQL 8.x                   |
| Connectivity | JDBC (MySQL Connector/J)    |
| Architecture | MVC Pattern                 |
| Security     | SHA-256 (Java Security API) |
| Build Tools  | IntelliJ / Eclipse          |

---

## 🏗 Architecture Overview

```text
          ┌───────────────┐
          │     View      │
          │  (Swing GUI)  │
          └──────┬────────┘
                 │
                 ▼
          ┌───────────────┐
          │   Controller  │
          │  (Services)   │
          └──────┬────────┘
                 │
                 ▼
          ┌───────────────┐
          │     Model     │
          │ (DAO + Beans) │
          └───────────────┘
```

---

## 📁 Project Structure

```bash
DigitalWallet/
├── src/com/wallet/
│   ├── main/                 # Entry point
│   ├── gui/frames/           # UI Screens (Swing)
│   ├── beans/                # Data Models
│   ├── dao/                  # Database Layer
│   ├── service/              # Business Logic
│   ├── util/                 # Utilities (DB, Security, Session)
│   └── exceptions/           # Custom Exceptions
├── resources/database/       # SQL Schema
└── README.md
```

---

## ⚙️ Installation & Setup

### 🔹 Prerequisites

* Java JDK 17+ (Recommended: JDK 21)
* MySQL Server 8.x
* MySQL Connector/J (JAR)
* IDE (IntelliJ / Eclipse)

---

### 🔹 Database Setup

```sql
CREATE DATABASE digital_wallet;
```

```sql
SOURCE resources/database/schema.sql;
```

---

### 🔹 Run Application

1. Configure DB credentials in:

   ```
   DatabaseUtil.java
   ```
2. Add MySQL Connector JAR to classpath
3. Run:

   ```
   WalletApplication.java
   ```

---

## 🔒 Security Features

* 🔐 SHA-256 password hashing with salt
* 🛡 Fully parameterized SQL queries
* 🔄 ACID-compliant transaction system
* ⏱ Session timeout & secure logout

---

## 🧪 Testing & Quality Assurance

* ✅ Unit Testing (Service Layer)
* ✅ Integration Testing (Full workflows)
* ✅ Performance Testing (75 concurrent users)
* ✅ Security Testing (Static + penetration)
* ✅ UI Testing (Cross-platform Swing)

---

## 👥 Team Contributions

| Member               | Role                   |
| -------------------- | ---------------------- |
| Naina Yadav          | Integration & Security |
| Bithika Jain         | Transaction Logic      |
| Pradeepti Srivastava | UI Development         |
| Lavanya Pandit       | Authentication System  |
| Dashkrat Srivastava  | Database Layer         |

---

## 📚 Advanced Concepts Used

* OOP Principles (Encapsulation, Inheritance, Abstraction)
* JavaBeans Design Pattern
* JDBC (PreparedStatement, Transactions)
* Swing GUI Development
* Custom Exception Handling
* Multithreading (SwingWorker)
* MVC Architecture

---

## 📌 Project Details

| Field         | Details                             |
| ------------- | ----------------------------------- |
| Course        | Advanced Java Programming (CSE4019) |
| Institution   | VIT Bhopal University               |
| Academic Year | 2025–2026                           |
| Team          | Team #5                             |

---

## ⭐ Future Enhancements

* 🌐 Convert to Web App (Spring Boot + React)
* 📱 Mobile App Integration
* 🔔 Real-time Notifications
* 📊 Analytics Dashboard
* 🔐 OAuth / 2FA Authentication

---

## 📜 License

This project is developed for academic purposes. You may modify and extend it for learning.

---

## 💡 Show Your Support

If you like this project:

* ⭐ Star this repository
* 🍴 Fork it
* 🛠 Contribute
