package com.wallet.main;

// ============================================================
// Developed by Member 5 – Integration & Application Flow
// WalletApplication: The main entry point of the application.
// Sets the look-and-feel and launches the LoginFrame on the
// Swing Event Dispatch Thread (EDT) for thread safety.
// ============================================================

import com.wallet.gui.frames.LoginFrame;

import javax.swing.*;

public class WalletApplication {

    public static void main(String[] args) {
        // Run all Swing code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Use the system's native look-and-feel for a polished appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("[App] Could not set native look-and-feel: " + e.getMessage());
            }

            System.out.println("===========================================");
            System.out.println("  Secure Digital Wallet Application v1.0  ");
            System.out.println("  Advanced Java College Project            ");
            System.out.println("===========================================");

            // Launch the login screen
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}