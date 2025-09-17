package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.ChangeEmailRequest;
import org.chapeullah.dto.ChangePasswordRequest;
import org.chapeullah.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CredentialsController {

    private final UserService userService;

    public CredentialsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/exist/{id}")
    public void userExist(@PathVariable Integer id) {
        userService.userExist(id);
    }

    @PatchMapping("/credentials/update/password")
    public void updatePassword(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        userService.changePassword(
                UserController.parseAuthHeader(authHeader),
                changePasswordRequest.oldPassword(),
                changePasswordRequest.newPassword()
        );
    }

    @PatchMapping("/credentials/update/email")
    public void updateEmail(
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
