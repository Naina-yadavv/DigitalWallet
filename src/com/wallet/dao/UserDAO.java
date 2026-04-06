package com.wallet.dao;

// ============================================================
// Developed by Member 3 – Database Layer
// UserDAO: Data Access Object for the 'users' table.
// All SQL for user records lives here — NO SQL in service
// or GUI classes. Uses PreparedStatement throughout to
// prevent SQL injection attacks.
// ============================================================

import com.wallet.beans.UserBean;
import com.wallet.util.DatabaseUtil;

import java.sql.*;

public class UserDAO {

    // ---- INSERT ----

    /**
     * Inserts a new user into the database.
     * @return true if the insert succeeded
     */
    public boolean insertUser(UserBean user) {
        String sql = "INSERT INTO users (name, email, password, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());   // Already hashed
            ps.setDouble(4, user.getBalance());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[UserDAO] insertUser failed: " + e.getMessage());
            return false;
        }
    }

    // ---- SELECT ----

    /**
     * Finds a user by their email address.
     * @return UserBean if found, null otherwise
     */
    public UserBean findByEmail(String email) {
        String sql = "SELECT id, name, email, password, balance FROM users WHERE email = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("[UserDAO] findByEmail failed: " + e.getMessage());
        }
        return null;
    }

    /**
     * Finds a user by their primary key (ID).
     * @return UserBean if found, null otherwise
     */
    public UserBean findById(int userId) {
        String sql = "SELECT id, name, email, password, balance FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("[UserDAO] findById failed: " + e.getMessage());
        }
        return null;
    }

    /**
     * Checks whether an email address is already registered.
     */
    public boolean emailExists(String email) {
        return findByEmail(email) != null;
    }

    // ---- UPDATE ----

    /**
     * Updates a user's balance.
     * Called inside TransactionDAO transactions — the caller
     * passes in an open Connection so this update participates
     * in the same atomic transaction.
     *
     * @param conn   open Connection (managed by caller)
     * @param userId the user whose balance to update
     * @param newBalance the new balance value
     */
    public void updateBalance(Connection conn, int userId, double newBalance) throws SQLException {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    /**
     * Standalone balance update (opens its own connection).
     * Used for "Add Money" which doesn't need a full transaction.
     */
    public boolean updateBalance(int userId, double newBalance) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[UserDAO] updateBalance failed: " + e.getMessage());
            return false;
        }
    }

    // ---- HELPER ----

    /** Maps the current ResultSet row to a UserBean. */
    private UserBean mapRow(ResultSet rs) throws SQLException {
        return new UserBean(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getDouble("balance")
        );
    }
}
