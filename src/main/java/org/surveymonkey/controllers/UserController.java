package org.surveymonkey.controllers;

import org.springframework.ui.Model;
import org.surveymonkey.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.repositories.UserRepository;

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user/create")
    public String createUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return "redirect:/"; // Add view for success message that presents home or logon buttons
    }

    @PostMapping(value = "/user/logon")
    public String logonUser(Model model, @RequestParam String name){
        // Add user to model, potentially check if user exists first and send error page if no user ?
        model.addAttribute("user", userRepository.findByName(name));
        return "redirect:/"; // Add view for user management (create, close survey)
    }
}
