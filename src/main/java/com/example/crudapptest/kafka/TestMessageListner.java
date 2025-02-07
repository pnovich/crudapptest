package com.example.crudapptest.kafka;

import com.example.crudapptest.service.PersonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class TestMessageListner {
    private static final Logger log = Logger.getLogger(PersonService.class.getName());

    private List<String> messages = new ArrayList<>();
    @KafkaListener(topics = "test", groupId = "123")
    public void listen(String message) {
        log.info("Received: " + message);
        messages.add(message);
    }

    public List<String> getAllMessages() {
        return this.messages;
    }
}
