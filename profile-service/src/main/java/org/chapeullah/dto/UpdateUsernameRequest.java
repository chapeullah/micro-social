package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsernameRequest(@NotBlank String username) {}
