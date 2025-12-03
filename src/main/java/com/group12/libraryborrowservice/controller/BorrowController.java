package com.group12.libraryborrowservice.controller;

import com.group12.libraryborrowservice.dto.BorrowRequest;
import com.group12.libraryborrowservice.dto.BorrowResponse;
import com.group12.libraryborrowservice.service.IBorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST API controller for handling borrow operations.
 *
 * This class represents the API LAYER of the system.
 * It exposes HTTP endpoints and delegates the work to the service layer.
 */
@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    // Service layer dependency (business logic)
    private final IBorrowService borrowService;

    // Constructor Injection
    public BorrowController(IBorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /**
     * POST /api/borrow
     *
     * Accepts a JSON body (BorrowRequest) and starts the borrow process.
     * Returns a professional JSON response (BorrowResponse).
     */
    @PostMapping
    public ResponseEntity<BorrowResponse> borrowBook(@RequestBody BorrowRequest request) {

        // 1. Simple validation for incoming request
        if (request.getUserId() == null || request.getBookId() == null) {
            return ResponseEntity.badRequest()
                    .body(new BorrowResponse("FAILED",
                            "ERROR: userId and bookId are required!",
                            null));
        }

        // 2. Service Call (Business Logic)
        boolean result = borrowService.createBorrowRecord(request.getUserId(), request.getBookId());

        // 3. Return Professional JSON Response with Transaction ID
        if (result) {
            String trackingId = UUID.randomUUID().toString();

            BorrowResponse response = new BorrowResponse(
                    "SUCCESS",
                    "Operation Successful! Book borrowing process initiated via RabbitMQ.",
                    trackingId
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500)
                    .body(new BorrowResponse("ERROR",
                            "An internal error occurred.",
                            null));
        }
    }

    /**
     * GET /api/borrow
     *
     * This prevents users from seeing the white-label error page.
     * It shows a friendly message instead of 404 or 405.
     */
    @GetMapping
    public String borrowInfo() {
        return "This endpoint only supports POST. Please send a POST request to /api/borrow.";
    }
}
