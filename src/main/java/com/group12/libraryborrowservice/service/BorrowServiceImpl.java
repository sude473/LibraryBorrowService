package com.group12.libraryborrowservice.service;

import com.group12.libraryborrowservice.entity.BorrowRecord;
import com.group12.libraryborrowservice.repository.BorrowRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl implements IBorrowService {

    private final BorrowRepository borrowRepository;
    private final RabbitTemplate rabbitTemplate;

    public BorrowServiceImpl(BorrowRepository borrowRepository, RabbitTemplate rabbitTemplate) {
        this.borrowRepository = borrowRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public boolean createBorrowRecord(Long userId, Long bookId) {
        try {
            System.out.println("--- TRANSACTION STARTED ---");


            BorrowRecord newRecord = new BorrowRecord(userId, bookId);
            borrowRepository.save(newRecord);
            System.out.println("DATABASE: Saved record ID: " + newRecord.getId());


            String message = "User " + userId + " borrowed Book " + bookId;
            rabbitTemplate.convertAndSend("borrowQueue", message);
            System.out.println("RABBITMQ: Message sent to queue: " + message);

            return true;
        } catch (Exception e) {

            System.err.println("ERROR: " + e.getMessage());

            return true;
        }
    }
}