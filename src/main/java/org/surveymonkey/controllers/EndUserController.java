package org.surveymonkey.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
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

    @PostMapping(value = "/index/user/success")
    public String createUserConfirmed(@RequestParam String name) {
        EndUser endUser = new EndUser();
        endUser.setName(name);
        endUserService.save(endUser);
        LOG.info("Created a new user with the name " + name);
        return "redirect:/index/logon";
    }

    @PostMapping(value = "/user")
    public String logonConfirmed(@RequestParam String name, Model model) {
        // Add user to model, potentially check if user exists first and send error page if no user?

        if (endUserService.findByName(name) != null) {
            model.addAttribute("user", endUserService.findByName(name));
            LOG.info("Logged on a user with the name: " + name);
            return "userManagement"; // Add view for user management (create, close survey)
        }
        LOG.error("An error occured when trying to logon a user with the name " + name + ". The user does not exist");
        return "error"; // error page for now
    }

    @GetMapping(value = "/index/create")
    public String createUser() {
        LOG.trace("Request to create user, returning createUser template");
        return "createUser";
    }

    @GetMapping(value = "/index/logon")
    public String logonUser() {
        LOG.trace("Request to login user, returning logonPage template");
        return "logonPage";
    }

    @GetMapping(value = "/endusercontroller/test")
    @ResponseBody
    public String testEndUserController() {
        LOG.debug("Testing EndUserController");
        return "EndUserController is working";
    }

    @PostMapping(value = "/user/{userId}/displayAll")
    public String displayAllSurveys(Model model, @PathVariable long userId){
        EndUser user = endUserService.findById(userId);
        if (user == null) {
            LOG.error("An error occured when trying to get surveys for a user that does not exist");
            LOG.trace("returning to / mapping");
            return "redirect:/";
        }


        // Find user by userid and then return a list display of all surveys
        model.addAttribute("user", user);
        model.addAttribute("surveys", user.getSurveys());
        LOG.trace("Request to view all surveys for user: " + user.getName() + "returning displayUserSurveys template");
        return "displayUserSurveys";
    }
}