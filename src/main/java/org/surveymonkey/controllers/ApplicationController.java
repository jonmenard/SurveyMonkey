package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.services.iservices.IEndUserService;

@Controller
public class ApplicationController {

    @Autowired
    private IEndUserService endUserService;

    @ModelAttribute("isUserSignedIn")
    public boolean isUserSignedIn(@CookieValue(value = "user_id", defaultValue = "-1") String user_id) {
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        return (user != null);
    }
}
