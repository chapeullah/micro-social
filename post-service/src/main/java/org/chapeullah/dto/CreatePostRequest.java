package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(@NotBlank String content) {}
