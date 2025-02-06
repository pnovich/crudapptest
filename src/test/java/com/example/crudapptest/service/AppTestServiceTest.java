package com.example.crudapptest.service;

import com.example.crudapptest.entity.AppHomePage;
import com.example.crudapptest.repository.AppHomePageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppTestServiceTest {

    @Mock
    private AppHomePageRepository appHomePageRepository;

    @InjectMocks
    private AppTestService appTestService;

    AppHomePage page1;

    AppHomePage page2;

    @BeforeEach
    public void setup(){
        page1 = new AppHomePage(1L,"app works, this is default string");
        page2 = new AppHomePage(2L,"this is test url");
    }

    @Test
    void getHomeMessage() {
        when(appHomePageRepository.findById(page1.getId()))
                .thenReturn(Optional.ofNullable(page1));

        String expected = page1.getPagePart();
        String actual = appTestService.getHomeMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTestMessage() {
        when(appHomePageRepository.findById(page2.getId()))
                .thenReturn(Optional.ofNullable(page2));

        String expected = page2.getPagePart();
        String actual = appTestService.getTestMessage();
        Assertions.assertEquals(expected, actual);

    }
}