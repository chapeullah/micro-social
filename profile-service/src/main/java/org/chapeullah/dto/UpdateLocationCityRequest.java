package org.chapeullah.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateLocationCityRequest(@NotBlank String city) {}
