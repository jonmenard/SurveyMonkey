package org.surveymonkey.integration;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import org.springframework.util.Assert;







@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Integration {

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
        System.out.println((response.getBody()));
        Assert.isTrue(response.getBody().equals("UserController is working"),"UserController is not working");
    }

    @Test
    public void testTextQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/TextQuestionController/test",
                String.class);
        System.out.println((response.getBody()));
        Assert.isTrue(response.getBody().equals("TextQuestionController is working"),"TextQuestionController is not working");
    }

    @Test
    public void testSurveyControllerController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/SurveyControllerController/test",
                String.class);
        System.out.println((response.getBody()));
        Assert.isTrue(response.getBody().equals("SurveyController is working"),"SurveyControllerController is not working");
    }

    @Test
    public void testNumberQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/NumberQuestionController/test",
                String.class);
        System.out.println((response.getBody()));
        Assert.isTrue(response.getBody().equals("NumberQuestionController is working"),"NumberQuestionController is not working");
    }

    @Test
    public void testChoiceQuestionController() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/ChoiceQuestionController/test",
                String.class);
        System.out.println((response.getBody()));
        Assert.isTrue(response.getBody().equals("ChoiceQuestionController is working"),"ChoiceQuestionController is not working");
    }

//    @Test
//    public void test() throws Exception {
//        ResponseEntity<String> response = template.getForEntity(base.toString() + "/index/create",
//                String.class);
//        System.out.println((response.getBody()));
//        Assert.isTrue(response.getBody().equals("<!DOCTYPE HTML>\n" +
//                "<html xmlns:th=\"http://www.thymeleaf.org\" lang=\"en\">\n" +
//                "<head>\n" +
//                "   <title>SurveyMonkey</title>\n" +
//                "   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<form action=\"/index/user/success\" method=\"post\" id=\"userCreateForm\">\n" +
//                "   <label for=\"name\">Username:</label><br>\n" +
//                "   <input type=\"text\" id=\"name\" name=\"name\"><br>\n" +
//                "   <button type=\"submit\" form=\"userCreateForm\" value=\"Submit\">Create a new User</button>\n" +
//                "</form>\n" +
//                "</body>\n" +
//                "</html>"),"/index/create returns the wrong template: Thymeleaf is possibly not working");
//    }






//    @GetMapping(value = "/index/logon")
//    public String logonUser() {
//        return "logonPage";
//    }



}
