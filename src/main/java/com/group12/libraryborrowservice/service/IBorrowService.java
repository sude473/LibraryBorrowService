package com.group12.libraryborrowservice.service;

public interface IBorrowService {
    // Defines the contract for borrowing a book
    boolean createBorrowRecord(Long userId, Long bookId);
}