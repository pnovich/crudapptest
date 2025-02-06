package com.example.crudapptest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppHomePage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String pagePart;

    public AppHomePage() {
    }

    public AppHomePage(String pagePart) {
        this.pagePart = pagePart;
    }

    public AppHomePage(Long id, String pagePart) {
        this.id = id;
        this.pagePart = pagePart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPagePart() {
        return pagePart;
    }

    public void setPagePart(String pagePart) {
        this.pagePart = pagePart;
    }
}
