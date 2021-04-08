package org.surveymonkey.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.services.iservices.IEndUserService;
import org.surveymonkey.services.iservices.IQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;

import java.net.URL;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITControllers {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ISurveyService surveyService;

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    private IQuestionService questionService;

    @BeforeEach
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void testErrorsController() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/errorscontroller/test", String.class);
        Assert.isTrue(Objects.equals(response.getBody(), "ErrorsController is working"), "ErrorsController is not working");
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

    @Test
    public void testSurveyService() {
        List<Survey> usersSurveys = surveyService.findSurveysByUser(endUserService.findById(953));
        boolean isTrue = true;
        for(int i = 0; i < usersSurveys.size(); i++){
            if(usersSurveys.get(i).getEndUserId() != 953){
                isTrue = false;
            }
        }
        System.out.println(isTrue + "----------------------------------------------------------------------");
        Assert.isTrue(isTrue, "get User's survey is not working");
    }

    @Test
    public void testEndUserService() {
        EndUser endUser = endUserService.findByName("testingAccount");
        Assert.isTrue((endUser.getEndUserId() == 953), "get enduser by name is not working");
    }

    @Test
    public void testQuestionService() {
        Question question = questionService.findById(941);
        Assert.isTrue(question.getQuestion().equals("Is this survey working?"), "get question by id is not working");
    }



}

