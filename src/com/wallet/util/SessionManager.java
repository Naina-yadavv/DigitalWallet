package com.wallet.util;

// ============================================================
// Developed by Naina Yadav 24BCE10400 – Integration & Application Flow
// SessionManager: Simple in-memory session manager.
// Tracks the currently logged-in user across all GUI frames.
// Only one user can be logged in at a time (single-session).
// ============================================================

import com.wallet.beans.UserBean;

public class SessionManager {

    // The currently authenticated user; null if no one is logged in
    private static UserBean currentUser = null;

    // Private constructor — static utility class
    private SessionManager() {}

    /**
     * Starts a session for the given user (called on successful login).
     */
    public static void login(UserBean user) {
        currentUser = user;
        System.out.println("[Session] User logged in: " + user.getEmail());
    }

    /**
     * Ends the current session (called on logout).
     */
    public static void logout() {
        if (currentUser != null) {
            System.out.println("[Session] User logged out: " + currentUser.getEmail());
        }
        currentUser = null;
    }

    /**
     * Returns the currently logged-in user, or null if no session is active.
     */
    public static UserBean getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns true if a user is currently logged in.
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Updates the balance of the current session user without re-fetching from DB.
     * Called after every transaction to keep the UI in sync.
     */
    public static void updateBalance(double newBalance) {
        if (currentUser != null) {
            currentUser.setBalance(newBalance);
        }
    }
}
