package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.ChangeEmailRequest;
import org.chapeullah.dto.ChangePasswordRequest;
import org.chapeullah.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user/credentials")
public class CredentialsController {

    private final UserService userService;

    public CredentialsController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change/password")
    public void changePassword(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        userService.changePassword(
                UserController.parseAuthHeader(authHeader),
                changePasswordRequest.oldPassword(),
                changePasswordRequest.newPassword()
        );
    }

    @PostMapping("/change/email")
    public void changeEmail(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ChangeEmailRequest changeEmailRequest
    ) {
        userService.changeEmail(
                UserController.parseAuthHeader(authHeader),
                changeEmailRequest.newEmail(),
                changeEmailRequest.password()
        );
    }

}
