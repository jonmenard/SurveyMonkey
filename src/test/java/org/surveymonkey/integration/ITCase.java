package org.surveymonkey.integration;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCase {

    @LocalServerPort
    private int port;

    private URL base;



    @Autowired
    private TestRestTemplate  template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    @Test
    public void testUserController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/userController/test",
                String.class);
        Assert.isTrue(response.getBody().equals("UserController is working"),"UserController is not working");
    }

    @Test
    public void testTextQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/TextQuestionController/test",
                String.class);
        Assert.isTrue(response.getBody().equals("TextQuestionController is working"),"TextQuestionController is not working");
    }

    @Test
    public void testSurveyControllerController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/SurveyControllerController/test",
                String.class);
        Assert.isTrue(response.getBody().equals("SurveyController is working"),"SurveyControllerController is not working");
    }

    @Test
    public void testNumberQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/NumberQuestionController/test",
                String.class);
        Assert.isTrue(response.getBody().equals("NumberQuestionController is working"),"NumberQuestionController is not working");
    }

    @Test
    public void testChoiceQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/ChoiceQuestionController/test",
                String.class);
        Assert.isTrue(response.getBody().equals("ChoiceQuestionController is working"),"ChoiceQuestionController is not working");
    }
}
