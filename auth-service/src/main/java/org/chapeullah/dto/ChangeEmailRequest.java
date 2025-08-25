package org.chapeullah.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChangeEmailRequest(
        @NotBlank String jwtToken,
        @Email String newEmail,
        @NotBlank String password
) {}
