package com.wallet.util;

// ============================================================
// Developed by Naina Yadav 24BCE10400
// DatabaseUtil: Manages JDBC connections to MySQL.
// Provides a single static method to get a Connection,
// keeping database configuration in one place.
// ============================================================

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // ---- Database configuration ----
    private static final String HOST     = "localhost";
    private static final String PORT     = "3306";
    private static final String DATABASE = "digital_wallet";
    private static final String USER     = "root";
    private static final String PASSWORD = "Root2005";  

    private static final String URL =
        "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
        + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    // Static block: load the MySQL JDBC driver once at class load time
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[DB] MySQL JDBC driver loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println("[DB] ERROR: MySQL JDBC Driver not found. Add mysql-connector-java to classpath.");
            e.printStackTrace();
        }
    }

    // Private constructor — utility class should not be instantiated
    private DatabaseUtil() {}

    /**
     * Returns a new JDBC Connection to the digital_wallet database.
     * Caller is responsible for closing the connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Convenience method: rolls back a connection silently.
     * Useful in catch blocks to undo partial transactions.
     */
    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("[DB] Rollback failed: " + e.getMessage());
            }
        }
    }

    /**
     * Convenience method: closes a connection silently.
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("[DB] Close failed: " + e.getMessage());
            }
        }
    }
}
