package DigitalWallet.src.com.wallet.beans;

// ============================================================
// Developed by 24BCE10236 – Transaction & Security Logic
// TransactionBean: JavaBean representing a single wallet
// transaction (send, receive, or add money).
// ============================================================

import java.sql.Timestamp;

public class TransactionBean {

    private int       id;
    private int       senderId;
    private int       receiverId;
    private double    amount;
    private String    type;          // "SEND", "RECEIVE", "ADD_MONEY"
    private String    description;
    private Timestamp createdAt;

    // ---- Constructors ----

    public TransactionBean() {}

    // Constructor used when inserting a new transaction
    public TransactionBean(int senderId, int receiverId, double amount, String type, String description) {
        this.senderId   = senderId;
        this.receiverId = receiverId;
        this.amount     = amount;
        this.type       = type;
        this.description = description;
    }

    // Full constructor (used when loading from database)
    public TransactionBean(int id, int senderId, int receiverId,
                           double amount, String type, String description, Timestamp createdAt) {
        this.id          = id;
        this.senderId    = senderId;
        this.receiverId  = receiverId;
        this.amount      = amount;
        this.type        = type;
        this.description = description;
        this.createdAt   = createdAt;
    }

    // ---- Getters ----

    public int       getId()          { return id; }
    public int       getSenderId()    { return senderId; }
    public int       getReceiverId()  { return receiverId; }
    public double    getAmount()      { return amount; }
    public String    getType()        { return type; }
    public String    getDescription() { return description; }
    public Timestamp getCreatedAt()   { return createdAt; }

    // ---- Setters ----

    public void setId(int id)                     { this.id = id; }
    public void setSenderId(int senderId)          { this.senderId = senderId; }
    public void setReceiverId(int receiverId)      { this.receiverId = receiverId; }
    public void setAmount(double amount)           { this.amount = amount; }
    public void setType(String type)               { this.type = type; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatedAt(Timestamp createdAt)  { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "TransactionBean{id=" + id + ", type='" + type + "', amount=" + amount + ", date=" + createdAt + "}";
    }
}
