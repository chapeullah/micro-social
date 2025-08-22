package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.LoginRequest;
import org.chapeullah.dto.RegisterRequest;
import org.chapeullah.dto.UserResponse;
import org.chapeullah.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(
                registerRequest.email(),
                registerRequest.password()
        );
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(
                loginRequest.email(),
                loginRequest.password()
        );
    }
}
