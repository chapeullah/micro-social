package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateFieldRequest(@NotBlank String field) {}
