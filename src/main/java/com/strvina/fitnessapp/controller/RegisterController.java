package com.strvina.fitnessapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.strvina.fitnessapp.model.User;
import com.strvina.fitnessapp.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping
    public String processRegistration(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        userService.saveUser(user);
        return "redirect:/login?success";
    }
}
