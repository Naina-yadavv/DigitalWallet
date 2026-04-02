# Secure Digital Wallet Application
### Advanced Java College Project

---

## Project Description

A desktop-based Secure Digital Wallet application built using Core Java with a Swing GUI. Users can register, login, check their wallet balance, add money, send money to other registered users, and view their complete transaction history. The application follows the MVC (Model–View–Controller) architecture and applies key Advanced Java concepts including JDBC, JavaBeans, OOP, Exception Handling, and Basic Security.

---

## Features

| User Registration | Create an account with name, email, and password |

| Secure Login | Password hashed with SHA-256 before storage |

| Wallet Balance | View current balance on the dashboard |

| Add Money | Top-up your wallet (max ₹1,00,000 per transaction) |

| Send Money | Transfer funds to another user by email |

| Transaction History | Full history displayed in a JTable |

| SQL Injection Prevention | All queries use PreparedStatement |

| JDBC Transactions | Send-money uses commit/rollback for atomicity |

| Custom Exception | InsufficientBalanceException for overdraft cases |

| Session Management | In-memory SessionManager tracks logged-in user |

---

## Tech Stack

- **Language:** Java 17+
- **GUI:** Java Swing
- **Database:** MySQL 8.x
- **JDBC Driver:** mysql-connector-java 8.x
- **Architecture:** MVC (Model–View–Controller)
- **Security:** SHA-256 (java.security.MessageDigest)
- **Build:** Manual javac / any IDE (IntelliJ, Eclipse, NetBeans)

---

## Project Structure

```
DigitalWallet/
├── src/com/wallet/
│   ├── main/
│   │   └── WalletApplication.java        ← Entry point
│   ├── gui/frames/
│   │   ├── LoginFrame.java               ← Login screen
│   │   ├── RegisterFrame.java            ← Registration screen
│   │   ├── DashboardFrame.java           ← Main wallet dashboard
│   │   └── SendMoneyFrame.java           ← Send money dialog
│   ├── beans/
│   │   ├── UserBean.java                 ← User data model
│   │   └── TransactionBean.java          ← Transaction data model
│   ├── dao/
│   │   ├── UserDAO.java                  ← DB ops for users
│   │   └── TransactionDAO.java           ← DB ops for transactions
│   ├── service/
│   │   ├── AuthenticationService.java    ← Register/Login logic
│   │   └── TransactionService.java       ← Wallet business logic
│   ├── util/
│   │   ├── DatabaseUtil.java             ← JDBC connection manager
│   │   ├── PasswordUtil.java             ← SHA-256 hashing
│   │   └── SessionManager.java          ← Login session tracker
│   └── exceptions/
│       └── InsufficientBalanceException.java
├── resources/database/
│   └── schema.sql                        ← MySQL schema
└── README.md
```

---

## Setup Instructions

### Step 1: Install Prerequisites
- Java JDK 17 or higher
- MySQL Server 8.x
- MySQL Connector/J JAR (`mysql-connector-java-8.x.x.jar`)

### Step 2: Set Up Database
```sql
-- Open MySQL Workbench or terminal and run:
source resources/database/schema.sql
```
This creates the `digital_wallet` database with `users` and `transactions` tables.

### Step 3: Configure Database Credentials
Open `src/com/wallet/util/DatabaseUtil.java` and update:
```java
private static final String USER     = "root";       // Your MySQL username
private static final String PASSWORD = "";            // Your MySQL password
```

### Step 4: Add MySQL Driver to Classpath
Download `mysql-connector-java-8.x.x.jar` and add it to your IDE's build path.

**IntelliJ IDEA:** File → Project Structure → Libraries → Add JAR

**Eclipse:** Right-click project → Build Path → Add External JARs

### Step 5: Run the Application
Run `WalletApplication.java` as the main class.

---

## Team Contribution

| Pradeepti Srivastava - 24BCE11109 | UI Developer | LoginFrame, RegisterFrame, DashboardFrame, SendMoneyFrame |

| Member 2 | Authentication & User Logic | UserBean, AuthenticationService |

| Member 3 | Database Layer | UserDAO, TransactionDAO, schema.sql |

| Bithika Jain - 24BCE10236 | Transaction & Wallet Logic | TransactionBean, TransactionService, InsufficientBalanceException |

| Naina Yadav - 24BCE10400 | Integration, security & Application Flow | WalletApplication, SessionManager,DatabseUtil, PasswordUtil |

---

## Advanced Java Topics Covered

- **OOP:** Encapsulation (beans), Inheritance, Abstraction (service layer)
- **JavaBeans:** UserBean, TransactionBean with standard getters/setters
- **JDBC:** Connection, PreparedStatement, ResultSet, commit/rollback
- **Swing:** JFrame, JPanel, JTable, JButton, SwingWorker (background threads)
- **Exception Handling:** Custom checked exception (InsufficientBalanceException), try-catch-finally
- **Security:** SHA-256 hashing via java.security.MessageDigest, PreparedStatement (SQL injection prevention)
- **MVC Architecture:** GUI (View), Service (Controller), DAO+Beans (Model)
- **Multi-threading:** SwingWorker for non-blocking DB calls on the EDT
