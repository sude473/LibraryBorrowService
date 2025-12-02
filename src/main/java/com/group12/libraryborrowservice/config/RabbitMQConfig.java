package com.group12.libraryborrowservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * RabbitMQ configuration class.
 *
 * This class belongs to the MESSAGING LAYER and defines the queue
 * that will be used by the application.
 */
@Configuration
public class RabbitMQConfig {

/**
     * Defines a queue named 'borrowQueue'.
     * This queue is used by BorrowServiceImpl when sending messages.
     *
     * @return Queue bean managed by Spring
     */
    @Bean
    public Queue borrowQueue() {
      
        return new Queue("borrowQueue", false);
    }
}
