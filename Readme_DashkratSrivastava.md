Here’s a **clean, upgraded, and slightly improved README.md** for your project with better structure, clarity, and a few professional enhancements (perfect for GitHub + viva) 👇

---

# 💳 Secure Digital Wallet Application

### Advanced Java (Desktop-Based Project)

---

## 📌 Project Overview

The **Secure Digital Wallet Application** is a desktop-based financial system developed using **Core Java and Swing GUI**. It enables users to securely manage digital transactions such as adding money, sending funds, and tracking transaction history.

The application is built using the **MVC (Model–View–Controller)** architecture and demonstrates key **Advanced Java concepts** like JDBC, JavaBeans, OOP, Exception Handling, and Security practices.

---

## ✨ Key Features

| Feature                     | Description                                         |
| --------------------------- | --------------------------------------------------- |
| 👤 User Registration        | Create a new account with name, email, and password |
| 🔐 Secure Login             | Passwords stored using SHA-256 hashing              |
| 💰 Wallet Balance           | Real-time balance display on dashboard              |
| ➕ Add Money                 | Add funds (limit: ₹1,00,000 per transaction)        |
| 💸 Send Money               | Transfer money using recipient email                |
| 📊 Transaction History      | View all transactions in tabular format             |
| 🛡 SQL Injection Prevention | Uses PreparedStatement for all queries              |
| 🔄 Transaction Management   | Commit/Rollback ensures safe transactions           |
| ⚠ Custom Exception          | Handles insufficient balance scenarios              |
| 🧠 Session Management       | Tracks logged-in user during runtime                |

---

## 🛠 Tech Stack

* **Language:** Java 17+
* **GUI:** Java Swing
* **Database:** MySQL 8.x
* **JDBC Driver:** MySQL Connector/J
* **Architecture:** MVC (Model–View–Controller)
* **Security:** SHA-256 (`MessageDigest`)
* **IDE Support:** IntelliJ / Eclipse / NetBeans

---

## 📂 Project Structure

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
└── README.md
```

---

## ⚙️ Setup Instructions

### 🔹 Step 1: Install Requirements

* Java JDK 17+
* MySQL Server 8.x
* MySQL Connector/J (JAR file)

---

### 🔹 Step 2: Setup Database

Run the following in MySQL:

```sql
source resources/database/schema.sql;
```

This will create:

* `digital_wallet` database
* Required tables: `users`, `transactions`

---

### 🔹 Step 3: Configure Database Credentials

Open:

```
DatabaseUtil.java
```

Update:

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

### 🔹 Step 4: Add MySQL Driver

Add `mysql-connector-java-8.x.x.jar` to your project:

* **IntelliJ:** File → Project Structure → Libraries
* **Eclipse:** Build Path → Add External JAR

---

### 🔹 Step 5: Run the Application

Run:

```
WalletApplication.java
```

---

## 👨‍💻 Team Contributions

| Member               | Role                   | Responsibilities                  |
| -------------------- | ---------------------- | --------------------------------- |
| Pradeepti Srivastava | UI Developer           | All GUI Frames                    |
| Lavanya Pandit       | Authentication Logic   | UserBean, AuthenticationService   |
| Dashkrat Srivastava  | Database Layer         | DAO Classes + SQL Schema          |
| Bithika Jain         | Transaction Logic      | TransactionService, Exception     |
| Naina Yadav          | Integration & Security | Main App, Session, Security Utils |

---

## 📚 Concepts Demonstrated

### 🔹 Object-Oriented Programming

* Encapsulation using JavaBeans
* Abstraction via Service Layer

### 🔹 JDBC

* Connection handling
* PreparedStatement
* ResultSet
* Transaction control (commit/rollback)

### 🔹 Java Swing

* JFrame, JPanel, JTable
* Event Handling
* Responsive UI

### 🔹 Exception Handling

* Custom Exception: `InsufficientBalanceException`

### 🔹 Security

* SHA-256 Password Hashing
* SQL Injection Prevention

### 🔹 Architecture

* MVC Pattern

  * **Model:** Beans + DAO
  * **View:** GUI Frames
  * **Controller:** Services

### 🔹 Multithreading

* SwingWorker for background DB operations

---

## 🚀 Future Enhancements

* 🔐 Two-Factor Authentication (2FA)
* 🌐 REST API version (Spring Boot)
* 📱 Mobile App Integration
* 📊 Analytics Dashboard
* 💳 UPI / Payment Gateway Integration

---

## 📌 Conclusion

This project demonstrates a **real-world financial system simulation** using Advanced Java concepts. It ensures **security, modularity, and scalability**, making it ideal for academic evaluation and practical learning.

---
