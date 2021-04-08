package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.services.iservices.IEndUserService;

import java.net.InetAddress;

@Controller
public class ApplicationController {

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    Environment environment;

    @ModelAttribute("isUserSignedIn")
    public boolean isUserSignedIn(@CookieValue(value = "user_id", defaultValue = "-1") String user_id) {
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        return (user != null);
    }

    @ModelAttribute("hostName")
    public String hostname() {
        return InetAddress.getLoopbackAddress().getHostAddress();
    }
}
