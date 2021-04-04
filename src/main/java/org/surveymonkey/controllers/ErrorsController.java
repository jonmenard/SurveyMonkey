package org.surveymonkey.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorStatusCodeStr = "";
        if (errorStatusCode != null) {
            errorStatusCodeStr = " " + errorStatusCode.toString();
        }

        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String errorMessageStr = "Something went wrong";
        if (errorMessage != null && errorMessage != "") {
            errorMessageStr = errorMessage.toString();
        }

        String errorInfo = "Error" + errorStatusCodeStr + ": " + errorMessageStr + ".";
        model.addAttribute("errorInfo", errorInfo);

        return "error";

    }

    @RequestMapping("/error/{customErrorMessage}")
    public String handleCustomError(@PathVariable String customErrorMessage, Model model) {
        model.addAttribute("errorInfo", "Error: " + customErrorMessage + ".");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @GetMapping("/errorscontroller/test")
    @ResponseBody
    public String testThisController() {
        return "ErrorsController is working";
    }

}