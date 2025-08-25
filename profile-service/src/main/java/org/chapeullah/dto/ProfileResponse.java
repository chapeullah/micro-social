package org.chapeullah.dto;

import org.chapeullah.entity.Profile;

import java.time.Instant;
import java.time.LocalDate;

public record ProfileResponse(
        Integer userId,
        String username,
        Instant registeredAt,
        LocalDate birthday,
        String locationCountry,
        String locationCity
) {
    public ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getUserId(),
                profile.getUsername(),
                profile.getRegisteredAt(),
                profile.getBirthday(),
                profile.getLocationCountry(),
                profile.getLocationCity()
        );
    }
}
