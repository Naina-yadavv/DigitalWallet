package com.wallet.gui.frames;

// ============================================================
// Developed by Pradeepti Srivastava 24BCE11109 – UI Developer
// SendMoneyFrame: Dialog-style frame for sending money to
// another registered user by email. Calls TransactionService
// and handles InsufficientBalanceException gracefully.
// ============================================================

import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.service.TransactionService;
import com.wallet.util.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SendMoneyFrame extends JFrame {

    private final TransactionService transactionService = new TransactionService();
    private final DashboardFrame     dashboardFrame;   // Reference to refresh after transfer
    private final DecimalFormat      df = new DecimalFormat("#,##0.00");

    // UI Components
    private JTextField receiverEmailField;
    private JTextField amountField;
    private JLabel     balanceLabel;
    private JLabel     statusLabel;
    private JButton    sendButton;
    private JButton    cancelButton;

    public SendMoneyFrame(DashboardFrame dashboardFrame) {
        this.dashboardFrame = dashboardFrame;
        initUI();
    }

    private void initUI() {
        setTitle("Send Money");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboardFrame);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 4, 0);
        gbc.gridwidth = 2;

        // ---- Title ----
        JLabel title = new JLabel("Send Money", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        // ---- Current Balance Display ----
        gbc.gridy = 1;
        double balance = SessionManager.getCurrentUser() != null
            ? SessionManager.getCurrentUser().getBalance() : 0.0;
        balanceLabel = new JLabel("Your Balance: ₹" + df.format(balance), SwingConstants.CENTER);
        balanceLabel.setForeground(new Color(0, 100, 0));
        panel.add(balanceLabel, gbc);

        // ---- Receiver Email ----
        gbc.gridy = 2;
        panel.add(new JLabel("Recipient Email:"), gbc);
        gbc.gridy = 3;
        receiverEmailField = new JTextField(20);
        panel.add(receiverEmailField, gbc);

        // ---- Amount ----
        gbc.gridy = 4;
        panel.add(new JLabel("Amount (₹):"), gbc);
        gbc.gridy = 5;
        amountField = new JTextField(20);
        panel.add(amountField, gbc);

        // ---- Status ----
        gbc.gridy = 6;
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(statusLabel, gbc);

        // ---- Buttons ----
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        sendButton   = new JButton("Send");
        cancelButton = new JButton("Cancel");
        btnPanel.add(sendButton);
        btnPanel.add(cancelButton);
        gbc.gridy = 7;
        panel.add(btnPanel, gbc);

        // ---- Listeners ----
        sendButton.addActionListener(e -> handleSendMoney());
        cancelButton.addActionListener(e -> dispose());

        add(panel);
    }

    private void handleSendMoney() {
        String receiverEmail = receiverEmailField.getText().trim();
        String amountStr     = amountField.getText().trim();

        sendButton.setEnabled(false);
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setText("Processing...");

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            private boolean insufficientFunds = false;
            private String  insufficientMsg   = "";

            @Override
            protected String doInBackground() {
                try {
                    return transactionService.sendMoney(receiverEmail, amountStr);
                } catch (InsufficientBalanceException e) {
                    // Catch custom exception and store its message
                    insufficientFunds = true;
                    insufficientMsg   = e.getMessage();
                    return null;   // Will be treated specially below
                }
            }

            @Override
            protected void done() {
                try {
                    // Handle InsufficientBalanceException case
                    if (insufficientFunds) {
                        statusLabel.setForeground(Color.RED);
                        statusLabel.setText(insufficientMsg);
                        sendButton.setEnabled(true);
                        return;
                    }

                    String error = get();
                    if (error == null) {
                        // Success
                        JOptionPane.showMessageDialog(SendMoneyFrame.this,
                            "Money sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dashboardFrame.loadDashboard();   // Refresh dashboard
                        dispose();
                    } else {
                        statusLabel.setForeground(Color.RED);
                        statusLabel.setText(error);
                        sendButton.setEnabled(true);
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Unexpected error.");
                    sendButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }
}
