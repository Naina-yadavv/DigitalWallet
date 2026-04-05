package com.wallet.beans;

// ============================================================
// Developed by Member 2 – Authentication & User Logic
// UserBean: JavaBean representing a registered wallet user.
// Follows standard JavaBeans conventions (private fields,
// public getters/setters, no-arg constructor).
// ============================================================

public class UserBean {

    private int    id;
    private String name;
    private String email;
    private String password;   // Stored as SHA-256 hash
    private double balance;

    // No-arg constructor (required for JavaBeans)
    public UserBean() {}

    // Convenience constructor for creating new users
    public UserBean(String name, String email, String password) {
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.balance  = 0.0;
    }

    // Full constructor (used when loading from database)
    public UserBean(int id, String name, String email, String password, double balance) {
        this.id       = id;
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.balance  = balance;
    }

    // ---- Getters ----

    public int getId()           { return id; }
    public String getName()      { return name; }
    public String getEmail()     { return email; }
    public String getPassword()  { return password; }
    public double getBalance()   { return balance; }

    // ---- Setters ----

    public void setId(int id)              { this.id = id; }
    public void setName(String name)       { this.name = name; }
    public void setEmail(String email)     { this.email = email; }
    public void setPassword(String pass)   { this.password = pass; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "UserBean{id=" + id + ", name='" + name + "', email='" + email + "', balance=" + balance + "}";
    }
}
