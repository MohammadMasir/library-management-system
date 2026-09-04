package com.interview.library_management.controller.views;

import com.interview.library_management.dto.RegisterDto;
import com.interview.library_management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthViewController {

    private final AuthService authService;

    @GetMapping
    public String login(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model
    ){
        if (error != null) {model.addAttribute("errorMsg", "Invalid username or password!");}
        if (logout != null) {model.addAttribute("logoutMsg", "Logged out successfully!");}
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "auth/registration";
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult bindingResult,
            Model model
    ){
        authService.signup(registerDto);
        return "redirect:/auth";
    }

}
