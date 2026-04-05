package com.wallet.service;

// ============================================================
// Developed by Member 2 – Authentication & User Logic
// AuthenticationService: Business logic for user registration
// and login. Coordinates between UserDAO (DB access),
// PasswordUtil (hashing), and SessionManager (session state).
// ============================================================

import com.wallet.beans.UserBean;
import com.wallet.dao.UserDAO;
import com.wallet.util.PasswordUtil;
import com.wallet.util.SessionManager;

public class AuthenticationService {

    private final UserDAO userDAO = new UserDAO();

    /**
     * Registers a new user.
     *
     * Validation checks:
     *   - Name, email, password must not be blank
     *   - Email must not already be registered
     *   - Password is hashed before storage
     *
     * @return null on success; error message string on failure
     */
    public String register(String name, String email, String password) {
        // Input validation
        if (name == null || name.trim().isEmpty())         return "Name cannot be empty.";
        if (email == null || email.trim().isEmpty())       return "Email cannot be empty.";
        if (password == null || password.trim().isEmpty()) return "Password cannot be empty.";
        if (!email.contains("@") || !email.contains(".")) return "Enter a valid email address.";
        if (password.length() < 6)                         return "Password must be at least 6 characters.";

        String normalizedEmail = email.trim().toLowerCase();

        // Check for duplicate email
        if (userDAO.emailExists(normalizedEmail)) {
            return "Email is already registered. Please login.";
        }

        // Hash password before storing
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Create bean and persist
        UserBean newUser = new UserBean(name.trim(), normalizedEmail, hashedPassword);
        boolean saved = userDAO.insertUser(newUser);

        if (!saved) return "Registration failed due to a database error. Try again.";

        System.out.println("[Auth] New user registered: " + normalizedEmail);
        return null;   // null = success
    }

    /**
     * Authenticates a user and starts a session.
     *
     * @param email    user's email
     * @param password plain-text password entered by the user
     * @return null on success; error message string on failure
     */
    public String login(String email, String password) {
        if (email == null || email.trim().isEmpty())       return "Email cannot be empty.";
        if (password == null || password.trim().isEmpty()) return "Password cannot be empty.";

        String normalizedEmail = email.trim().toLowerCase();
        UserBean user = userDAO.findByEmail(normalizedEmail);

        // User not found
        if (user == null) {
            return "No account found with that email.";
        }

        // Verify password hash
        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            return "Incorrect password. Please try again.";
        }

        // Start session
        SessionManager.login(user);
        System.out.println("[Auth] Login successful: " + normalizedEmail);
        return null;   // null = success
    }

    /**
     * Logs out the current user and clears the session.
     */
    public void logout() {
        SessionManager.logout();
    }
}
