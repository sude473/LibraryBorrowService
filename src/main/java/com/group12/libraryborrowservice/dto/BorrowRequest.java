package com.group12.libraryborrowservice.dto;

/**
 * Data Transfer Object used to receive borrow requests from the client.
 *
 * This class is used as the request body in BorrowController.
 */
public class BorrowRequest {

    private Long userId;   // ID of the user
    private Long bookId;   // ID of the book

    /**
     * Default constructor is required for JSON deserialization.
     */
    public BorrowRequest() {
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
}
