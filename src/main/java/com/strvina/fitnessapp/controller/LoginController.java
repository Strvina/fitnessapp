package com.strvina.fitnessapp.controller;

import com.strvina.fitnessapp.model.User;
import com.strvina.fitnessapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping
    public String processLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User foundUser = userService.findByEmail(user.getEmail());

        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loggedInUser", foundUser);
            return "redirect:UserProfileForm/form";
        } else {
            model.addAttribute("loginError", "Pogrešan email ili lozinka");
            return "user/login";
        }
    }
}
