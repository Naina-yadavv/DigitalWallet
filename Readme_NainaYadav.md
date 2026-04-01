# Member 5 – Integration & Security
## Secure Digital Wallet Application

### Personal Details
- **Name:** Naina Yadav
- **Registration Number:** 24BCE10400
- **Role:** Integration & Security Developer

---

### Files I Worked On
- `src/com/wallet/main/WalletApplication.java`
- `src/com/wallet/util/SessionManager.java`
- `src/com/wallet/util/PasswordUtil.java`
- `src/com/wallet/util/DatabaseUtil.java`
- `resources/database/schema.sql`
- `README.md`

---

### Features I Implemented
- WalletApplication: main entry point, sets native
  look-and-feel, launches LoginFrame on the Swing
  Event Dispatch Thread for thread safety
- SessionManager: tracks logged-in user across all
  frames, updates balance after every transaction
  without extra database calls
- PasswordUtil: SHA-256 password hashing and
  verification — plain text passwords never stored
- DatabaseUtil: central JDBC connection factory,
  rollback and close helper methods
- schema.sql: designed and executed the full database
  schema with tables, foreign keys and indexes
- README.md: wrote complete project documentation

---

### Technologies & Concepts I Used
- **SwingUtilities.invokeLater()** — launches GUI on EDT
- **UIManager.setLookAndFeel()** — native OS styling
- **SHA-256 Hashing** — MessageDigest, byte to hex
- **JDBC** — DriverManager, Connection management
- **Static Session Pattern** — in-memory login state
- **MySQL Schema Design** — tables, foreign keys, indexes
- **Git & GitHub** — repo creation, branch management,
  pull request reviews and merges

---

### How My Code Works 

**WalletApplication.java:**
Single entry point of the entire application. Uses
SwingUtilities.invokeLater() to schedule GUI creation
on the Event Dispatch Thread — mandatory in Java Swing
for thread safety. Also sets native OS look-and-feel
for a professional appearance on any operating system.

**SessionManager.java:**
Lightweight in-memory session store. After login it
holds the UserBean in a static field so every frame
can access current user data without hitting the
database repeatedly. updateBalance() keeps the session
in sync after every transaction.

**PasswordUtil.java:**
Uses SHA-256 — a one-way cryptographic hash function —
to hash passwords before storage. Even if the database
is leaked, plain text passwords cannot be recovered.
verifyPassword() hashes the entered password and
compares it to the stored hash.

**DatabaseUtil.java:**
Central JDBC connection factory. All DAO classes call
DatabaseUtil.getConnection() instead of managing their
own connections. Also provides rollback() and close()
helpers to keep DAO code clean and consistent.

**schema.sql:**
Defines the digital_wallet database with users and
transactions tables. Includes foreign key constraints
linking transactions to users, and indexes on sender_id
and receiver_id for fast transaction lookups.

---
