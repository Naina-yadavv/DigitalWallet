package com.wallet.gui.frames;

// ============================================================
// Developed by Pradeepti Srivastava 24BCE11109 – UI Developer
// RegisterFrame: New user registration screen.
// Collects name, email, and password, delegates validation
// and persistence to AuthenticationService.
// ============================================================

import com.wallet.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private final AuthenticationService authService = new AuthenticationService();

    // UI Components
    private JTextField     nameField;
    private JTextField     emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton        registerButton;
    private JButton        backButton;
    private JLabel         statusLabel;

    public RegisterFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Digital Wallet – Register");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 4, 0);
        gbc.gridwidth = 2;

        // ---- Title ----
        JLabel title = new JLabel("Create New Account", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        // ---- Name ----
        gbc.gridy = 1;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridy = 2;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // ---- Email ----
        gbc.gridy = 3;
        panel.add(new JLabel("Email Address:"), gbc);
        gbc.gridy = 4;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // ---- Password ----
        gbc.gridy = 5;
        panel.add(new JLabel("Password (min 6 characters):"), gbc);
        gbc.gridy = 6;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // ---- Confirm Password ----
        gbc.gridy = 7;
        panel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridy = 8;
        confirmPasswordField = new JPasswordField(20);
        panel.add(confirmPasswordField, gbc);

        // ---- Status ----
        gbc.gridy = 9;
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(statusLabel, gbc);

        // ---- Buttons ----
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        registerButton = new JButton("Create Account");
        backButton     = new JButton("Back to Login");
        btnPanel.add(registerButton);
        btnPanel.add(backButton);

        gbc.gridy = 10;
        panel.add(btnPanel, gbc);

        // ---- Listeners ----
        registerButton.addActionListener(e -> handleRegister());

        backButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private void handleRegister() {
        String name            = nameField.getText().trim();
        String email           = emailField.getText().trim();
        String password        = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Client-side confirm password check (extra UX validation)
        if (!password.equals(confirmPassword)) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Passwords do not match.");
            return;
        }

        registerButton.setEnabled(false);
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setText("Creating account...");

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                return authService.register(name, email, password);
            }

            @Override
            protected void done() {
                try {
                    String error = get();
                    if (error == null) {
                        // Success
                        JOptionPane.showMessageDialog(RegisterFrame.this,
                            "Account created successfully!\nYou can now login.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                        new LoginFrame().setVisible(true);
                        dispose();
                    } else {
                        statusLabel.setForeground(Color.RED);
                        statusLabel.setText(error);
                        registerButton.setEnabled(true);
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Unexpected error.");
                    registerButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }
}
