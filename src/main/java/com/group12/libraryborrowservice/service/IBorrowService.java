package com.group12.libraryborrowservice.service;
/**
 * Service interface for borrow operations.
 *
 * This interface defines the CONTRACT for the borrow service
 * and is implemented by BorrowServiceImpl.
 */
public interface IBorrowService {
    
    /**
     * Creates a borrow record for the given user and book.
     *
     * @param userId ID of the user who borrows the book
     * @param bookId ID of the borrowed book
     * @return true if the operation is successful, false otherwise
     */
    boolean createBorrowRecord(Long userId, Long bookId);
}
