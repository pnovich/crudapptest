package com.example.crudapptest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AppTestController {
    private static final Logger log = Logger.getLogger(AppTestController.class.getName());
    @GetMapping("/")
    public String defaultString() {
        return "app works, this is default string";
    }

    @GetMapping("/test")
    public String testUrl() {
        return "this is test url";
    }

}
