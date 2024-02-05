package com.famous.flightreservation.controllers;

import com.famous.flightreservation.entities.User;
import com.famous.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "login/login";
    }
}
