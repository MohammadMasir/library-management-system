package com.interview.library_management.controller;

import com.interview.library_management.dto.LoginDto;
import com.interview.library_management.dto.RegisterDto;
import com.interview.library_management.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signupUser(@RequestBody @Valid RegisterDto authDto) {
        authService.signup(authDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody @Valid LoginDto loginDto,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        authService.login(loginDto, request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}