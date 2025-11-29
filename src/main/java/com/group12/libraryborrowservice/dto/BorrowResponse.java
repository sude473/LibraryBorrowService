package com.group12.libraryborrowservice.dto;

import java.time.LocalDateTime;

public class BorrowResponse {
    private String status;
    private String message;
    private String transactionId;
    private String timestamp;


    public BorrowResponse(String status, String message, String transactionId) {
        this.status = status;
        this.message = message;
        this.transactionId = transactionId;
        this.timestamp = LocalDateTime.now().toString();
    }


    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public String getTransactionId() { return transactionId; }
    public String getTimestamp() { return timestamp; }
}