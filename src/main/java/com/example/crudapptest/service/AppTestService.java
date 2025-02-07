package com.example.crudapptest.service;

import com.example.crudapptest.entity.AppHomePage;
import com.example.crudapptest.repository.AppHomePageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class AppTestService {
    private static final Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    AppHomePageRepository appHomePageRepository;

    @PostConstruct
    public void init() {
        AppHomePage appHomePage1 = new AppHomePage("app works, this is default string");
        AppHomePage appHomePage2 = new AppHomePage("this is test url");
        List<AppHomePage> list = Arrays.asList(appHomePage1, appHomePage2);
        appHomePageRepository.saveAll(list);
    }
    public String getHomeMessage() {
        return getMessageFromDb(1L);
    }

    public String getTestMessage() {
        return getMessageFromDb(2L);
    }

    private String getMessageFromDb(Long id) {
        Optional<AppHomePage> optionalAppHomePage = appHomePageRepository.findById(id);
        AppHomePage page;
        if (optionalAppHomePage.isPresent()) {
        page= optionalAppHomePage.get();
        } else {
            return "no such page";
        }
        return page.getPagePart();
    }

    public void saveAll(List<AppHomePage> list) {
        appHomePageRepository.saveAll(list);
    }

    public void deleteAll() {
        appHomePageRepository.deleteAll();
    }
}
