package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UsersService;

@Controller
public class AuthController {
    private final UsersService usersService;

    @Autowired
    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    private String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getAuthenticated() {
        return "login";
    }
}
