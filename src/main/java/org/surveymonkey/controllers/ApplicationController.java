package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.surveymonkey.kafka.KafkaAnalytics;
import org.surveymonkey.kafka.Message;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.services.iservices.IEndUserService;
import org.surveymonkey.services.iservices.IKafkaAnalyticsService;

@Controller
public class ApplicationController {

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    Environment environment;

    @Autowired
    private Producer producer;



    @ModelAttribute("isUserSignedIn")
    public boolean isUserSignedIn(@CookieValue(value = "user_id", defaultValue = "-1") String user_id) {
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        sendMessage("updatesAndInserts","select");
        return (user != null);
    }

    @ModelAttribute("currentUserID")
    public long currentUserID(@CookieValue(value = "user_id", defaultValue = "-1") String user_id) {
        if(user_id == "-1") return -1;
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        sendMessage("updatesAndInserts","select");
        if(user != null) return user.getEndUserId();
        return -1;
    }

    @ModelAttribute("hostName")
    public String hostname() {
        return "https://sysc4806groupproject.herokuapp.com";
    }




    public void sendMessage(String topic, String message){
        producer.send(topic, new Message(0, message));
    }
}
