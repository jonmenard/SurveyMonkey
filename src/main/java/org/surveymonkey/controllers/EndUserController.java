package org.surveymonkey.controllers;

import org.springframework.ui.Model;
import org.surveymonkey.models.EndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.services.iservices.IEndUserService;

@Controller
public class EndUserController {

    @Autowired
    private IEndUserService endUserService;

    @PostMapping(value = "/index/user/success")
    public String createUserConfirmed(@RequestParam String name) {
        EndUser endUser = new EndUser();
        endUser.setName(name);
        endUserService.save(endUser);
        return "redirect:/index/logon";
    }

    @PostMapping(value = "/user")
    public String logonConfirmed(@RequestParam String name, Model model) {
        // Add user to model, potentially check if user exists first and send error page if no user?
        if (endUserService.findByName(name) != null) {
            model.addAttribute("user", endUserService.findByName(name));
            return "redirect:/"; // Add view for user management (create, close survey)
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

}