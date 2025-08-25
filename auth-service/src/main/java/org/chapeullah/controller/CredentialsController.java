package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.ChangeEmailRequest;
import org.chapeullah.dto.ChangePasswordRequest;
import org.chapeullah.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user/credentials")
public class CredentialsController {

    private final UserService userService;

    public CredentialsController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change/password")
    public void changePassword(
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        userService.changePassword(
                changePasswordRequest.jwtToken(),
                changePasswordRequest.oldPassword(),
                changePasswordRequest.newPassword()
        );
    }

    @PostMapping("/change/email")
    public void changeEmail(
            @Valid @RequestBody ChangeEmailRequest changeEmailRequest
    ) {
        userService.changeEmail(
                changeEmailRequest.jwtToken(),
                changeEmailRequest.newEmail(),
                changeEmailRequest.password()
        );
    }

}
