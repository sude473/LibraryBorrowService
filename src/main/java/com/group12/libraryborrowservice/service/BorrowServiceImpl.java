package com.group12.libraryborrowservice.service;

import com.group12.libraryborrowservice.entity.BorrowRecord;
import com.group12.libraryborrowservice.repository.BorrowRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
/**
 * Service implementation for borrow operations.
 *
 * This class represents the BUSINESS LAYER of the system.
 * It coordinates:
 *  - Data layer (BorrowRepository / database)
 *  - Messaging layer (RabbitMQ via RabbitTemplate)
 */
@Service
public class BorrowServiceImpl implements IBorrowService {

    // Repository responsible for interacting with the database (data layer)
    private final BorrowRepository borrowRepository;

    // RabbitTemplate is used to send messages to RabbitMQ (messaging layer)
    private final RabbitTemplate rabbitTemplate;

    /**
     * Constructor injection for required dependencies.
     * Spring automatically injects BorrowRepository and RabbitTemplate beans.
     */
    public BorrowServiceImpl(BorrowRepository borrowRepository, RabbitTemplate rabbitTemplate) {
        this.borrowRepository = borrowRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Creates a new borrow record and sends a message to RabbitMQ.
     *
     * @param userId ID of the user who borrows the book
     * @param bookId ID of the borrowed book
     * @return true if operation is successful, false otherwise
     */
    @Override
    public boolean createBorrowRecord(Long userId, Long bookId) {
        try {
            // Log the beginning of the transaction for debugging purposes
            System.out.println("--- TRANSACTION STARTED ---");

            // 1) Create a new BorrowRecord entity and save it to the database
            BorrowRecord newRecord = new BorrowRecord(userId, bookId);
            borrowRepository.save(newRecord);
            System.out.println("DATABASE: Saved record ID: " + newRecord.getId());

            // 2) Prepare a simple text message for the messaging layer
            String message = "User " + userId + " borrowed Book " + bookId;

            // 3) Send the message to the RabbitMQ queue (defined in RabbitMQConfig)
            rabbitTemplate.convertAndSend("borrowQueue", message);
            System.out.println("RABBITMQ: Message sent to queue: " + message);

            // Everything is successful -> inform the controller with 'true'
            return true;
        } catch (Exception e) {
            // If anything goes wrong (DB or RabbitMQ), log the error
            System.err.println("ERROR: " + e.getMessage());

            // Inform the controller that an error occurred
           
            return false;
        }
    }
}
