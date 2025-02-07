package com.example.crudapptest.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStringMessage(String message) {
        kafkaTemplate.send("test",message);
    }
}
