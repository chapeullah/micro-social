package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateLocationCountryRequest(@NotBlank String country) {}
