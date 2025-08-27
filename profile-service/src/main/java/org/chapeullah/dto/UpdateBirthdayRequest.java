package org.chapeullah.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UpdateBirthdayRequest(@NotNull @Past LocalDate birthday) {}
