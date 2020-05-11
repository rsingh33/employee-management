package com.home.employeemanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
       Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

       if(status != null){
        int statusCode =   Integer.valueOf(status.toString());
        switch (statusCode){
            case 404:
                return "error";
            case 403:
                return "error";
            case 500:
                return "error";
        }
       }

       return "error";
    }
}
