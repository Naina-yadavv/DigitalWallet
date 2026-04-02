package com.wallet.exceptions;

// ============================================================
// Developed by Bithika Jain (24BCE10236) – Transaction & Security Logic
// InsufficientBalanceException: Custom checked exception
// thrown when a user tries to send more money than their
// current wallet balance allows.
// ============================================================

public class InsufficientBalanceException extends Exception {

    private final double requestedAmount;
    private final double availableBalance;

    // Constructor with descriptive message
    public InsufficientBalanceException(double requested, double available) {
        super(String.format(
            "Insufficient balance. Requested: ₹%.2f | Available: ₹%.2f",
            requested, available
        ));
        this.requestedAmount  = requested;
        this.availableBalance = available;
    }

    // Constructor with custom message
    public InsufficientBalanceException(String message) {
        super(message);
        this.requestedAmount  = 0;
        this.availableBalance = 0;
    }

    public double getRequestedAmount()  { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}
