package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(@NotBlank String content) {}
