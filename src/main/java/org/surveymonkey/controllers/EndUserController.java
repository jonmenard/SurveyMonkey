package org.surveymonkey.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.kafka.Message;
import org.surveymonkey.models.EndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.services.iservices.IEndUserService;

@Controller
public class EndUserController {


    private static final Logger LOG = LoggerFactory.getLogger(EndUserController.class);

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    private Producer producer;


    private final String TOPIC = "EndUser";

    @PostMapping(value = "/index/user/success")
    public String createUserConfirmed(@RequestParam String name) {
        EndUser endUser = new EndUser();
        endUser.setName(name);
        endUserService.save(endUser);

        String message = "Creating a new user with the name " + name;
        producer.send(TOPIC,new Message(0, message));
        return "redirect:/index/logon";
    }

    @PostMapping(value = "/user")
    public String logonConfirmed(@RequestParam String name, Model model) {
        // Add user to model, potentially check if user exists first and send error page if no user?

        if (endUserService.findByName(name) != null) {
            model.addAttribute("user", endUserService.findByName(name));
            return "userManagement"; // Add view for user management (create, close survey)
        }
        return "error"; // error page for now
    }

    @GetMapping(value = "/index/create")
    public String createUser() {
        return "createUser";
    }

    @GetMapping(value = "/index/logon")
    public String logonUser() {
        return "logonPage";
    }

    @GetMapping(value = "/endusercontroller/test")
    @ResponseBody
    public String testEndUserController() {
        return "EndUserController is working";
    }

    @PostMapping(value = "/user/{userId}/displayAll")
    public String displayAllSurveys(Model model, @PathVariable long userId){
        EndUser user = endUserService.findById(userId);
        if (user == null) {
            return "error";
        }

        // Find user by userid and then return a list display of all surveys
        model.addAttribute("user", user);
        model.addAttribute("surveys", user.getSurveys());
        return "displayUserSurveys";
    }
}