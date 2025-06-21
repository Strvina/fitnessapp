package com.strvina.fitnessapp.controller;

import com.strvina.fitnessapp.model.UserProfile;
import com.strvina.fitnessapp.model.User;
import com.strvina.fitnessapp.service.OpenAIService;
import com.strvina.fitnessapp.service.UserProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/UserProfileForm")
public class UserProfileController {

    private final UserProfileService profileService;
    private final OpenAIService openAIService;

    public UserProfileController(UserProfileService profileService, 
                             OpenAIService openAIService) {
    this.profileService = profileService;
    this.openAIService = openAIService; 
    }

    @GetMapping("/form")
    public String showUserProfileForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "user/userProfileForm";
    }

    @PostMapping("/save")
    public String saveProfile(@ModelAttribute("userProfile") UserProfile profile,
            HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        profile.setUserId(loggedInUser.getId());
        profileService.save(profile);

        String prompt = buildPrompt(profile);
        String aiResponse = openAIService.generatePlan(prompt); // Promenjen servis

        model.addAttribute("aiResponse", aiResponse);
        return "user/planResult";
    }

    private String buildPrompt(UserProfile profile) {
    String restrictions = profile.getDietRestrictions().isEmpty()
        ? "None"
        : profile.getDietRestrictions().stream()
            .map(r -> r.getRestrictionType() + ": " + r.getItemName())
            .reduce((a, b) -> a + ", " + b)
            .orElse("None");

    return """
        You are a professional personal fitness trainer. Create a detailed and clear personalized weekly fitness and nutrition plan based on the following user profile:

        - Goal: %s
        - Training type: %s
        - Weight: %d kg
        - Height: %d cm
        - Dietary restrictions: %s

        Requirements:
        - Provide a **weekly training plan** organized in a Markdown table with columns: Week, Exercises, Sets, Reps.
        - Provide a **daily nutrition plan** organized in a separate Markdown table with columns: Meal, Description/Options.
        - Include sections for Supplements and Recovery Tips using bullet points.
        - Format the output clearly using Markdown syntax (headings, bold text, tables, lists).
        - Keep the language simple and motivational.
        - The output will be displayed inside a <pre> tag, so use Markdown formatting.

        Generate the complete plan in Markdown.
        """.formatted(
            profile.getGoal(),
            profile.getTrainingType(),
            profile.getWeight(),
            profile.getHeight(),
            restrictions
        );
}



}
    