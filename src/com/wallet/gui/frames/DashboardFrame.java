package com.wallet.gui.frames;

// ============================================================
// Developed by Pradeepti Srivastava 24BCE11109  – UI Developer
// DashboardFrame: Main screen shown after login.
// Displays balance, quick-action buttons, and a full
// transaction history in a JTable.
// ============================================================

import com.wallet.beans.TransactionBean;
import com.wallet.beans.UserBean;
import com.wallet.service.AuthenticationService;
import com.wallet.service.TransactionService;
import com.wallet.util.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class DashboardFrame extends JFrame {

    private final TransactionService transactionService = new TransactionService();
    private final AuthenticationService authService    = new AuthenticationService();
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    // UI Components
    private JLabel   welcomeLabel;
    private JLabel   balanceLabel;
    private JButton  sendMoneyButton;
    private JButton  addMoneyButton;
    private JButton  logoutButton;
    private JTable   transactionTable;
    private DefaultTableModel tableModel;

    public DashboardFrame() {
        initUI();
        loadDashboard();
    }

    private void initUI() {
        setTitle("Digital Wallet – Dashboard");
        setSize(680, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ---- Top Panel: welcome + balance ----
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        balanceLabel = new JLabel("Balance: ₹0.00");
        balanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        balanceLabel.setForeground(new Color(0, 128, 0));   // Green

        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(balanceLabel, BorderLayout.EAST);

        // ---- Button Panel ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        sendMoneyButton = new JButton("Send Money");
        addMoneyButton  = new JButton("Add Money");
        logoutButton    = new JButton("Logout");
        logoutButton.setForeground(Color.RED);

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(addMoneyButton);
        buttonPanel.add(logoutButton);

        // ---- Transaction Table ----
        String[] columns = {"#", "Type", "Amount (₹)", "Description", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        transactionTable = new JTable(tableModel);
        transactionTable.setRowHeight(24);
        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        transactionTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        transactionTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        transactionTable.getColumnModel().getColumn(3).setPreferredWidth(240);
        transactionTable.getColumnModel().getColumn(4).setPreferredWidth(140);

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Transaction History"));

        // ---- Layout ----
        JPanel mainPanel = new JPanel(new BorderLayout(0, 5));
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(topPanel,    BorderLayout.NORTH);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(northPanel,  BorderLayout.NORTH);
        mainPanel.add(scrollPane,  BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(mainPanel);

        // ---- Action Listeners ----
        sendMoneyButton.addActionListener(e -> {
            SendMoneyFrame sendFrame = new SendMoneyFrame(this);
            sendFrame.setVisible(true);
        });

        addMoneyButton.addActionListener(e -> handleAddMoney());

        logoutButton.addActionListener(e -> {
            authService.logout();
            new LoginFrame().setVisible(true);
            dispose();
        });
    }

    /** Populates welcome label, balance, and transaction table. */
    public void loadDashboard() {
        UserBean user = SessionManager.getCurrentUser();
        if (user == null) return;

        welcomeLabel.setText("Welcome, " + user.getName() + "!");
        balanceLabel.setText("Balance: ₹" + df.format(user.getBalance()));

        // Load transactions on background thread
        SwingWorker<List<TransactionBean>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<TransactionBean> doInBackground() {
                return transactionService.getTransactionHistory();
            }

            @Override
            protected void done() {
                try {
                    List<TransactionBean> transactions = get();
                    tableModel.setRowCount(0);   // Clear old rows

                    int rowNum = 1;
                    for (TransactionBean tx : transactions) {
                        tableModel.addRow(new Object[]{
                            rowNum++,
                            tx.getType(),
                            "₹" + df.format(tx.getAmount()),
                            tx.getDescription(),
                            tx.getCreatedAt().toString().substring(0, 19)
                        });
                    }

                    if (transactions.isEmpty()) {
                        tableModel.addRow(new Object[]{"", "No transactions yet.", "", "", ""});
                    }

                } catch (Exception ex) {
                    System.err.println("[Dashboard] Failed to load transactions: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    /** Prompts for add-money amount, calls TransactionService. */
    private void handleAddMoney() {
        String amountStr = JOptionPane.showInputDialog(this,
            "Enter amount to add (₹):", "Add Money", JOptionPane.PLAIN_MESSAGE);

        if (amountStr == null || amountStr.trim().isEmpty()) return;

        String error = transactionService.addMoney(amountStr.trim());
        if (error == null) {
            // Refresh balance label from updated session
            UserBean user = SessionManager.getCurrentUser();
            balanceLabel.setText("Balance: ₹" + df.format(user.getBalance()));
            loadDashboard();
            JOptionPane.showMessageDialog(this,
                "Money added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
