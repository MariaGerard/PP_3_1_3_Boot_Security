package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RolesService;
import ru.kata.spring.boot_security.demo.services.UsersService;
import ru.kata.spring.boot_security.demo.util.NewUserValidator;

import javax.validation.Valid;

@Controller
public class AdminsController {

    private final RolesService rolesService;
    private final UsersService usersService;
    private final NewUserValidator newUserValidator;


    @Autowired
    public AdminsController(RolesService rolesService, UsersService usersService, NewUserValidator newUserValidator) {
        this.rolesService = rolesService;
        this.usersService = usersService;
        this.newUserValidator = newUserValidator;
    }

    @GetMapping("/admin/user")
    public String showAllUsers(Model model) {
        model.addAttribute("user", usersService.findAll());
        return "all_users";
    }

    @GetMapping("/admin/{id}")
    public String showOneUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.findOne(id));
        return "show";
    }

    @GetMapping("/admin/new")
    public String showPageCreatingUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", rolesService.getRoles());
        return "new";
    }

    @PostMapping("/admin/user")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        newUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "new";
        }
        usersService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/{id}/update")
    public String showPageEditUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", usersService.findOne(id));
        model.addAttribute("role", rolesService.getRoles());
        return "update";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        usersService.update(user);
        return "redirect:user";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:user";
    }

}
