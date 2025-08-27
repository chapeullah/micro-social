package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.LoginRequest;
import org.chapeullah.dto.RegisterRequest;
import org.chapeullah.dto.UserResponse;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        return userService.register(
                registerRequest.email(),
                registerRequest.password()
        );
    }

    @PostMapping("/login")
    public UserResponse login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        return userService.login(
                loginRequest.email(),
                loginRequest.password()
        );
    }

    public static String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
