package org.surveymonkey.controllers;

import org.springframework.ui.Model;
import org.surveymonkey.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.services.iservices.IUserService;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/index/user/success")
    public String createUserConfirmed(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userService.save(user);
        return "redirect:/index/logon";
    }

    @PostMapping(value = "/user")
    public String logonConfirmed(Model model, @RequestParam String name) {
        // Add user to model, potentially check if user exists first and send error page if no user ?
        if (userService.findByName(name) != null) {
            model.addAttribute("user", userService.findByName(name));
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

}
