package com.wallet.util;

// ============================================================
// Developed by Naina Yadav 24BCE10400
// PasswordUtil: Provides SHA-256 password hashing.
// Plain-text passwords are NEVER stored in the database —
// only their 64-character hex hash is stored.
// ============================================================

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    // Private constructor — utility class
    private PasswordUtil() {}

    /**
     * Hashes a plain-text password using SHA-256.
     *
     * @param plainPassword the user's raw password
     * @return 64-character lowercase hex string (SHA-256 hash)
     * @throws RuntimeException if SHA-256 algorithm is unavailable (should never happen)
     */
    public static String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(plainPassword.getBytes());

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    /**
     * Verifies a plain-text password against a stored hash.
     *
     * @param plainPassword the password entered by the user
     * @param storedHash    the SHA-256 hash stored in the database
     * @return true if the password matches, false otherwise
     */
    public static boolean verifyPassword(String plainPassword, String storedHash) {
        if (plainPassword == null || storedHash == null) return false;
        return hashPassword(plainPassword).equals(storedHash);
    }
}
