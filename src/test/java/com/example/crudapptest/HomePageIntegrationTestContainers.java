package com.example.crudapptest;

import com.example.crudapptest.entity.AppHomePage;
import com.example.crudapptest.service.AppTestService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class HomePageIntegrationTestContainers {

//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgresContainer =
//            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));    @BeforeAll

//    static void beforeAll() {
//        postgresContainer.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgresContainer.stop();
//    }

    @Autowired
    AppTestService appTestService;

    @LocalServerPort
    private Integer port;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));

        AppHomePage appHomePage1 = new AppHomePage("app works, this is default string");
        AppHomePage appHomePage2 = new AppHomePage("this is test url");
        List<AppHomePage> list = Arrays.asList(appHomePage1, appHomePage2);

        appTestService.saveAll(list);
    }

    @AfterEach
    void clear() {
        appTestService.deleteAll();
    }

    @Test
    void shouldReturnMessageFromHomPage() {
        String title = "app works, this is default string";
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(title, response.getBody());
        System.out.println("rsponse" + response.getBody());
        System.out.println("done");

    }

}
