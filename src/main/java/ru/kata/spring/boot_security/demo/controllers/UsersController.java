package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UsersService;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/")
    private String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    public String authentication() {
        return "login";
    }

    @GetMapping("/user")
    public String showUserInfo(Model model) {
        User user = usersService.findOne();
        model.addAttribute("user", user);
        return "user";
    }

}
