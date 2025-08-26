package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateBirthdayRequest(@NotBlank LocalDate birthday) {}
