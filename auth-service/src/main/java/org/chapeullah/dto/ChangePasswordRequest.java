package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank String jwtToken,
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {}
