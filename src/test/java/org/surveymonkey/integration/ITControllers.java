package org.surveymonkey.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.net.URL;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITControllers {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void testEndUserController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/endusercontroller/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "EndUserController is working"), "EndUserController is not working");
    }

    @Test
    public void testTextQuestionController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/TextQuestionController/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "TextQuestionController is working"), "TextQuestionController is not working");
    }

    @Test
    public void testSurveyController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/SurveyControllerController/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "SurveyController is working"), "SurveyControllerController is not working");
    }

    @Test
    public void testNumberQuestionController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/NumberQuestionController/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "NumberQuestionController is working"), "NumberQuestionController is not working");
    }

    @Test
    public void testChoiceQuestionController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/ChoiceQuestionController/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "ChoiceQuestionController is working"), "ChoiceQuestionController is not working");
    }

}

