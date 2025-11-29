package com.group12.libraryborrowservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;
    private LocalDateTime borrowDate;

    // Boş Constructor (Hibernate için zorunludur)
    public BorrowRecord() {}

    // Kayıt oluştururken kullanacağımız Constructor
    public BorrowRecord(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = LocalDateTime.now(); // Şu anki zamanı otomatik atar
    }

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
}