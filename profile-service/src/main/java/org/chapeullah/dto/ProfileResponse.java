package org.chapeullah.dto;

import org.chapeullah.entity.Profile;

import java.time.Instant;
import java.time.LocalDate;

public record ProfileResponse(
        String username,
        Instant registeredAt,
        LocalDate birthday,
        String locationCountry,
        String locationCity
) {
    public static ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getUsername(),
                profile.getRegisteredAt(),
                profile.getBirthday(),
                profile.getLocationCountry(),
                profile.getLocationCity()
        );
    }
}
