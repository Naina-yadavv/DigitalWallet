# Member 4 – Transaction & Wallet Logic
## Secure Digital Wallet Application

### Personal Details
- **Name:** Bithika Jain
- **Roll Number:** 24BCE10236
- **Role:** Transaction & Wallet Logic Developer

---

### Files I Worked On
- `src/com/wallet/beans/TransactionBean.java`
- `src/com/wallet/service/TransactionService.java`
- `src/com/wallet/exceptions/InsufficientBalanceException.java`

---

### Features I Implemented
- TransactionBean: data model for a single wallet
  transaction — stores id, senderId, receiverId,
  amount, type, description and timestamp
- TransactionService.addMoney(): validates amount,
  enforces max top-up limit, calls DAO, updates
  session balance
- TransactionService.sendMoney(): validates recipient
  email and amount, prevents self-transfer, verifies
  receiver exists, delegates atomic transfer to DAO
- TransactionService.getTransactionHistory(): returns
  full transaction list for logged-in user
- InsufficientBalanceException: custom checked
  exception thrown when sender has insufficient
  funds — carries requested amount and available
  balance for meaningful error messages
- Business rules: max transfer ₹50,000, max top-up
  ₹1,00,000, amount must be above zero

---

### Technologies & Concepts I Used
- **JavaBeans** — TransactionBean with private fields,
  no-arg constructor, public getters and setters
- **Custom Exception** — extends Exception, carries
  extra fields for requested and available amounts
- **Exception Propagation** — InsufficientBalanceException
  not caught in service, propagates up to GUI layer
- **Service Layer Pattern** — all business validation
  before any database call is made
- **OOP** — clean separation of data model and logic

---

### How My Code Works 

**TransactionBean.java:**
JavaBean representing one wallet transaction. Follows
standard JavaBeans conventions — private fields,
no-arg constructor, public getters and setters.
Used by TransactionDAO to insert records and by
DashboardFrame to display transaction history.

**TransactionService.java:**
Handles all transaction business logic. sendMoney()
validates the recipient exists, the amount is within
limits, and the user isn't sending to themselves —
all before any database call. InsufficientBalanceException
is intentionally not caught here so it propagates
to the GUI where the user sees a meaningful error.

**InsufficientBalanceException.java:**
Custom checked exception thrown when a user tries
to send more than their current balance. It carries
two extra fields — requestedAmount and availableBalance
— so the error message tells the user exactly how
much they tried to send and how much they actually have.
Being a checked exception forces all callers to
explicitly handle this case.

---