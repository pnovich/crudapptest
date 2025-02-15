package com.example.crudapptest.controller;

import com.example.crudapptest.service.AppTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AppTestController {

    @Autowired
    private AppTestService appTestService;

    private static final Logger log = Logger.getLogger(AppTestController.class.getName());
//    @GetMapping("/")
//    public String defaultString() {
//        log.info("getting default string");
//        return appTestService.getHomeMessage();
//    }

    @GetMapping("/message")
        public String displayMessage(){
        return "message1";
    }

    @GetMapping("/test")
    public String testUrl() {
        log.info("getting testing url");
        return appTestService.getTestMessage();
    }

}
