package com.interview.library_management.service;

import com.interview.library_management.dto.RegisterDto;
import com.interview.library_management.model.User;
import com.interview.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(RegisterDto registerDto) {
        if (!registerDto.password().equals(registerDto.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords do not match"
            );
        }

        if (userRepository.existsByUsername(registerDto.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }
        User user = new User();
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setEmail(registerDto.email());
        user.setFullname(registerDto.fullname());
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

}