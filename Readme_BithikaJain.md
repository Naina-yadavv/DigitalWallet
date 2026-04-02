# Bithika Jain (24BCE10236) - Member 4
# Secure Digital Wallet Application  
### Java Desktop-Based Project  

---

## Project Description  

This project is a **Secure Digital Wallet system** built using Core Java and Swing for the graphical interface. It enables users to create accounts, log in securely, manage their wallet balance, deposit money, transfer funds to other users, and view their transaction records.

The application is structured using the **MVC (Model–View–Controller)** approach and demonstrates key Advanced Java concepts such as JDBC, JavaBeans, object-oriented programming, exception handling, and basic security practices.

---

## Features  

- **User Registration**  
  - New users can create an account using name, email, and password  

- **Secure Login System**  
  - Passwords are encrypted using SHA-256 before storing in the database  

- **Wallet Balance Display**  
  - Users can check their current balance after login  

- **Add Money**  
  - Users can deposit money (maximum ₹1,00,000 per transaction)  

- **Send Money**  
  - Transfer funds to another registered user using their email  

- **Transaction History**  
  - Displays all past transactions in a structured table format  

- **SQL Injection Protection**  
  - Uses PreparedStatement for safe database queries  

- **Transaction Safety**  
  - Implements commit and rollback for reliable money transfers  

- **Custom Exception Handling**  
  - Uses InsufficientBalanceException for low balance cases  

- **Session Handling**  
  - Tracks the logged-in user using SessionManager  

---

## Tech Stack  

- **Language:** Java (JDK 17+)  
- **GUI:** Java Swing  
- **Database:** MySQL  
- **Connectivity:** JDBC  
- **Architecture:** MVC Pattern  
- **Security:** SHA-256 hashing using MessageDigest  
- **Tools:** Any IDE (IntelliJ / Eclipse / NetBeans) or javac  

---

## Project Structure  

```
DigitalWallet/
├── src/com/wallet/
│   ├── main/
│   │   └── WalletApplication.java        ← Entry point of the program
│   ├── gui/frames/
│   │   ├── LoginFrame.java               ← Login UI
│   │   ├── RegisterFrame.java            ← Registration UI
│   │   ├── DashboardFrame.java           ← Main dashboard
│   │   └── SendMoneyFrame.java           ← Transfer interface
│   ├── beans/
│   │   ├── UserBean.java                 ← User data representation
│   │   └── TransactionBean.java          ← Transaction data representation
│   ├── dao/
│   │   ├── UserDAO.java                  ← User-related DB operations
│   │   └── TransactionDAO.java           ← Transaction-related DB operations
│   ├── service/
│   │   ├── AuthenticationService.java    ← Handles login and registration
│   │   └── TransactionService.java       ← Handles wallet operations
│   ├── util/
│   │   ├── DatabaseUtil.java             ← Database connection utility
│   │   ├── PasswordUtil.java             ← Password encryption logic
│   │   └── SessionManager.java           ← Maintains user session
│   └── exceptions/
│       └── InsufficientBalanceException.java
├── resources/database/
│   └── schema.sql                        ← SQL schema file
└── README.md
```

---

## Setup Instructions  

### Step 1: Install Required Software  
- Java JDK (version 17 or above)  
- MySQL Server  
- MySQL Connector/J (JDBC Driver)  

---

### Step 2: Database Configuration  

```sql
-- Run the following command in MySQL:
source resources/database/schema.sql
```

This will automatically create the database and necessary tables.

---

### Step 3: Update Credentials  

Go to `DatabaseUtil.java` and update:

```java
private static final String USER     = "root";
private static final String PASSWORD = "";
```

---

### Step 4: Add JDBC Driver  

- Add MySQL Connector JAR file to your project  

**For IntelliJ:** Project Structure → Libraries → Add JAR  
**For Eclipse:** Build Path → Add External JAR  

---

### Step 5: Run the Project  

- Execute `WalletApplication.java` to start the application  

---

## Team Contribution  

- **Member 1**  
  - Worked on user interface (Login, Register, Dashboard, Send Money screens)  

- **Member 2**  
  - Developed authentication logic and user-related classes  

- **Member 3**  
  - Handled database operations and SQL schema  

- **Bithika Jain (24BCE10236)**  
  - Implemented transaction processing and exception handling  

- **Naina Yadav (24BCE10400)**  
  - Managed integration, application flow, and security features  

---

## Advanced Java Concepts Used  

- **Object-Oriented Programming**  
  - Concepts like encapsulation, abstraction, and inheritance  

- **JavaBeans**  
  - Used for structured data handling (UserBean, TransactionBean)  

- **JDBC**  
  - Database connectivity using Connection, PreparedStatement, ResultSet  

- **Swing GUI**  
  - Used components like JFrame, JTable, JButton  

- **Exception Handling**  
  - Custom and built-in exceptions for error management  

- **Security Implementation**  
  - Password hashing and SQL injection prevention  

- **MVC Architecture**  
  - Separation of UI, logic, and data layers  

- **Multithreading**  
  - SwingWorker used for background database tasks  

---
