package com.wallet.gui.frames;

// ============================================================
// Developed by Member 1 – UI Developer
// LoginFrame: The application's entry-point screen.
// Collects email and password, delegates to
// AuthenticationService, then opens DashboardFrame on success.
// ============================================================

import com.wallet.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthenticationService authService = new AuthenticationService();

    // UI Components
    private JTextField     emailField;
    private JPasswordField passwordField;
    private JButton        loginButton;
    private JButton        registerButton;
    private JLabel         statusLabel;

    public LoginFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Digital Wallet – Login");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   // Center on screen
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        // ---- Title ----
        JLabel title = new JLabel("Secure Digital Wallet", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        // ---- Email ----
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridy = 2;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // ---- Password ----
        gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridy = 4;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // ---- Status label (shows errors) ----
        gbc.gridy = 5; gbc.gridwidth = 2;
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(statusLabel, gbc);

        // ---- Buttons ----
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loginButton    = new JButton("Login");
        registerButton = new JButton("Register");
        btnPanel.add(loginButton);
        btnPanel.add(registerButton);

        gbc.gridy = 6;
        panel.add(btnPanel, gbc);

        // ---- Action Listeners ----
        loginButton.addActionListener(e -> handleLogin());

        // Allow pressing Enter in password field to trigger login
        passwordField.addActionListener(e -> handleLogin());

        registerButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });

        add(panel);
    }

    /** Reads form fields, calls AuthenticationService, navigates on success. */
    private void handleLogin() {
        String email    = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Show loading state
        loginButton.setEnabled(false);
        statusLabel.setText("Logging in...");
        statusLabel.setForeground(Color.BLUE);

        // Perform login on a background thread to keep UI responsive
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                return authService.login(email, password);
            }

            @Override
            protected void done() {
                try {
                    String error = get();
                    if (error == null) {
                        // Success — open dashboard
                        new DashboardFrame().setVisible(true);
                        dispose();
                    } else {
                        statusLabel.setForeground(Color.RED);
                        statusLabel.setText(error);
                        loginButton.setEnabled(true);
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Unexpected error.");
                    loginButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }
}
