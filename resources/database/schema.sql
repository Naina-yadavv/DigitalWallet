-- ============================================================
-- Secure Digital Wallet Application - Database Schema
-- Developed by Member 3 – Database Layer
-- ============================================================

CREATE DATABASE IF NOT EXISTS digital_wallet;
USE digital_wallet;

-- Users table: stores registered wallet users
CREATE TABLE IF NOT EXISTS users (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)        NOT NULL,
    email       VARCHAR(150)        NOT NULL UNIQUE,
    password    VARCHAR(64)         NOT NULL,   -- SHA-256 hex hash
    balance     DECIMAL(15, 2)      NOT NULL DEFAULT 0.00,
    created_at  TIMESTAMP           DEFAULT CURRENT_TIMESTAMP
);

-- Transactions table: stores all wallet transactions
CREATE TABLE IF NOT EXISTS transactions (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    sender_id       INT             NOT NULL,
    receiver_id     INT             NOT NULL,
    amount          DECIMAL(15, 2)  NOT NULL,
    type            VARCHAR(20)     NOT NULL,   -- 'SEND', 'RECEIVE', 'ADD_MONEY'
    description     VARCHAR(255),
    created_at      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id)   REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- Index for fast transaction lookups by user
CREATE INDEX idx_sender   ON transactions(sender_id);
CREATE INDEX idx_receiver ON transactions(receiver_id);
