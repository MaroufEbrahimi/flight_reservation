package com.famous.flightreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }
}
