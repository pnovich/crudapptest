package com.example.crudapptest.controller;

import com.example.crudapptest.kafka.TestMessageProducer;
import com.example.crudapptest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class KafkaTestController {
    private static final Logger log = Logger.getLogger(PersonService.class.getName());


    @Autowired
    private TestMessageProducer testMessageProducer;
    @GetMapping("/send/{message}")
    public String sendMessageToKafka(@PathVariable
                                     String message) {
        log.info("message from controller " + message);

        try {
           testMessageProducer.sendStringMessage(message);
           log.info("message sent");
           return "message sent to topic";
        } catch (Exception e) {
            log.info("error");
            return "error";
        }
    }
}
