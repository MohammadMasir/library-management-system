package com.interview.library_management.controller.views;

import com.interview.library_management.dto.LoginDto;
import com.interview.library_management.dto.RegisterDto;
import com.interview.library_management.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

//    @PostMapping
//    public String login(
//            @Valid @ModelAttribute Model loginData,
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse
//            ){
//        String username = (String) loginData.getAttribute("username");
//        String password = (String) loginData.getAttribute("password");
//        authService.login( new LoginDto(
//                username,
//                password
//                ),httpServletRequest,httpServletResponse
//        );
//        return "homepage";
//    }

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
