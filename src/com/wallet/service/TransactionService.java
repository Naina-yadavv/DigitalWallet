package com.wallet.service;

// ============================================================
// Developed by 24BCE10236 – Transaction & Security Logic
// TransactionService: Business logic for wallet operations.
// Validates all input before delegating to TransactionDAO.
// Keeps transactional logic separate from the GUI layer.
// ============================================================

import com.wallet.beans.TransactionBean;
import com.wallet.beans.UserBean;
import com.wallet.dao.TransactionDAO;
import com.wallet.dao.UserDAO;
import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.util.SessionManager;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO        userDAO        = new UserDAO();

    /**
     * Adds money to the currently logged-in user's wallet.
     *
     * @param amountStr the amount as a String (from the text field)
     * @return null on success; error message on failure
     */
    public String addMoney(String amountStr) {
        UserBean currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) return "No user is logged in.";

        double amount;
        try {
            amount = Double.parseDouble(amountStr.trim());
        } catch (NumberFormatException e) {
            return "Enter a valid amount (e.g. 500.00).";
        }

        if (amount <= 0)        return "Amount must be greater than zero.";
        if (amount > 100000)    return "Maximum top-up per transaction is ₹1,00,000.";

        boolean success = transactionDAO.addMoney(currentUser.getId(), amount);
        if (!success) return "Transaction failed. Please try again.";

        // Refresh session balance
        double newBalance = currentUser.getBalance() + amount;
        SessionManager.updateBalance(newBalance);

        System.out.println("[Service] Added ₹" + amount + " to " + currentUser.getEmail());
        return null;   // success
    }

    /**
     * Sends money from the current user to another user identified by email.
     *
     * @param receiverEmail recipient's email
     * @param amountStr     amount as a String (from the text field)
     * @return null on success; error message on failure
     * @throws InsufficientBalanceException if sender doesn't have enough funds
     */
    public String sendMoney(String receiverEmail, String amountStr)
            throws InsufficientBalanceException {

        UserBean sender = SessionManager.getCurrentUser();
        if (sender == null) return "No user is logged in.";

        // Validate input
        if (receiverEmail == null || receiverEmail.trim().isEmpty()) return "Enter the recipient's email.";
        if (amountStr == null || amountStr.trim().isEmpty())         return "Enter an amount to send.";

        String normEmail = receiverEmail.trim().toLowerCase();

        if (normEmail.equals(sender.getEmail())) return "You cannot send money to yourself.";

        double amount;
        try {
            amount = Double.parseDouble(amountStr.trim());
        } catch (NumberFormatException e) {
            return "Enter a valid amount (e.g. 200.00).";
        }
        if (amount <= 0)     return "Amount must be greater than zero.";
        if (amount > 50000)  return "Maximum single transfer is ₹50,000.";

        // Look up receiver
        UserBean receiver = userDAO.findByEmail(normEmail);
        if (receiver == null) return "No user found with email: " + normEmail;

        // Perform atomic transfer
        try {
            double newBalance = transactionDAO.sendMoney(sender.getId(), receiver.getId(), amount);
            SessionManager.updateBalance(newBalance);
            System.out.println("[Service] Sent ₹" + amount + " from " + sender.getEmail() + " to " + normEmail);
            return null;   // success

        } catch (SQLException e) {
            System.err.println("[Service] sendMoney SQL error: " + e.getMessage());
            return "Transfer failed due to a server error. Please try again.";
        }
        // InsufficientBalanceException is NOT caught here — it propagates to the GUI
        // so the GUI can show the custom error message.
    }

    /**
     * Retrieves the full transaction history for the current user.
     */
    public List<TransactionBean> getTransactionHistory() {
        UserBean currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) return List.of();
        return transactionDAO.getTransactionsByUser(currentUser.getId());
    }
}
