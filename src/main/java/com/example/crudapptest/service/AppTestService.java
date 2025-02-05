package com.example.crudapptest.service;

import org.springframework.stereotype.Component;

@Component
public class AppTestService {
    public String getHomeMessage() {
        return "app works, this is default string";
    }

    public String getTestMessage() {
        return "this is test url";
    }
}
