package com.group12.libraryborrowservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class that represents a row in the 'borrow_records' table.
 *
 * This class belongs to the DATA LAYER and is managed by JPA/Hibernate.
 */
@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           // Primary key of the record

    private Long userId;       // ID of the user who borrowed the book
    private Long bookId;       // ID of the borrowed book

    private LocalDateTime borrowDate; // Timestamp when the borrow record is created

    /**
     * Default constructor required by JPA.
     */
    public BorrowRecord() {
    }

    /**
     * Convenience constructor used when creating a new borrow record
     * from the service layer.
     */
    public BorrowRecord(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
        // Automatically assign the current time when the record is created
        this.borrowDate = LocalDateTime.now();
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
}
