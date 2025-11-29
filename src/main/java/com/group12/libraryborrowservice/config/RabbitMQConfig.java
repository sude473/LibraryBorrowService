package com.group12.libraryborrowservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue borrowQueue() {
        return new Queue("borrowQueue", false);
    }
}