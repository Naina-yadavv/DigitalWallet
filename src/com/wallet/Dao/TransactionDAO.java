package com.wallet.dao;

// ============================================================
// Developed by Dashkrat Srivastava (24BCE11239) – Database Layer
// TransactionDAO: Data Access Object for the 'transactions'
// table. Handles insert and retrieval of transaction records.
// The critical send-money operation uses JDBC transactions
// (commit/rollback) to ensure atomicity.
// ============================================================

import com.wallet.beans.TransactionBean;
import com.wallet.beans.UserBean;
import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private final UserDAO userDAO = new UserDAO();

    // ---- INSERT (single transaction record, shared connection) ----

    /**
     * Inserts a transaction record using an existing open Connection.
     * Used inside the atomic sendMoney operation.
     */
    public void insertTransaction(Connection conn, TransactionBean tx) throws SQLException {
        String sql = "INSERT INTO transactions (sender_id, receiver_id, amount, type, description) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tx.getSenderId());
            ps.setInt(2, tx.getReceiverId());
            ps.setDouble(3, tx.getAmount());
            ps.setString(4, tx.getType());
            ps.setString(5, tx.getDescription());
            ps.executeUpdate();
        }
    }

    // ---- ATOMIC SEND MONEY (full JDBC transaction) ----

    /**
     * Transfers money from sender to receiver atomically.
     *
     * Steps performed inside ONE database transaction:
     *   1. Re-fetch sender's current balance (for accuracy)
     *   2. Check sufficient funds → throw InsufficientBalanceException if not
     *   3. Debit sender balance
     *   4. Credit receiver balance
     *   5. Insert SEND record for sender
     *   6. Insert RECEIVE record for receiver
     *   7. COMMIT — or ROLLBACK on any error
     *
     * @return sender's updated balance (used to refresh the session)
     */
    public double sendMoney(int senderId, int receiverId, double amount)
            throws InsufficientBalanceException, SQLException {

        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);   // Start transaction

            // Step 1: Fetch current balances from DB
            UserBean sender   = userDAO.findById(senderId);
            UserBean receiver = userDAO.findById(receiverId);

            if (sender == null || receiver == null) {
                throw new SQLException("Sender or receiver not found.");
            }

            double senderBalance   = sender.getBalance();
            double receiverBalance = receiver.getBalance();

            // Step 2: Validate balance
            if (senderBalance < amount) {
                throw new InsufficientBalanceException(amount, senderBalance);
            }

            // Step 3 & 4: Update balances
            userDAO.updateBalance(conn, senderId,   senderBalance   - amount);
            userDAO.updateBalance(conn, receiverId, receiverBalance + amount);

            // Step 5 & 6: Record transaction for both parties
            String desc = "Transfer to " + receiver.getName();
            insertTransaction(conn, new TransactionBean(senderId, receiverId, amount, "SEND",    desc));
            insertTransaction(conn, new TransactionBean(senderId, receiverId, amount, "RECEIVE", "Transfer from " + sender.getName()));

            conn.commit();   // All steps succeeded — commit
            System.out.println("[TransactionDAO] Transfer committed: ₹" + amount + " from " + senderId + " to " + receiverId);

            return senderBalance - amount;   // New sender balance

        } catch (InsufficientBalanceException e) {
            DatabaseUtil.rollback(conn);
            throw e;   // Re-throw so the service layer can handle it
        } catch (SQLException e) {
            DatabaseUtil.rollback(conn);
            System.err.println("[TransactionDAO] sendMoney rolled back: " + e.getMessage());
            throw e;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
                DatabaseUtil.close(conn);
            }
        }
    }

    // ---- ADD MONEY ----

    /**
     * Adds money to a user's wallet (top-up).
     * Inserts a transaction record with type "ADD_MONEY".
     * sender_id and receiver_id are both set to the user's own ID.
     */
    public boolean addMoney(int userId, double amount) {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            UserBean user = userDAO.findById(userId);
            if (user == null) return false;

            double newBalance = user.getBalance() + amount;
            userDAO.updateBalance(conn, userId, newBalance);

            TransactionBean tx = new TransactionBean(userId, userId, amount, "ADD_MONEY", "Wallet top-up");
            insertTransaction(conn, tx);

            conn.commit();
            return true;

        } catch (SQLException e) {
            DatabaseUtil.rollback(conn);
            System.err.println("[TransactionDAO] addMoney failed: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
                DatabaseUtil.close(conn);
            }
        }

    }

    // ---- SELECT ----

    /**
     * Returns all transactions for a given user
     * (both sent and received), ordered newest first.
     */
    public List<TransactionBean> getTransactionsByUser(int userId) {
        List<TransactionBean> list = new ArrayList<>();
        String sql = "SELECT id, sender_id, receiver_id, amount, type, description, created_at "
                   + "FROM transactions "
                   + "WHERE sender_id = ? OR receiver_id = ? "
                   + "ORDER BY created_at DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TransactionBean(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getDouble("amount"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("[TransactionDAO] getTransactionsByUser failed: " + e.getMessage());
        }
        return list;
    }
}
