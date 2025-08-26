package org.chapeullah.dto;

import org.chapeullah.entity.User;

import java.time.Instant;

public record UserResponse(String email, Instant registerDate, String accessToken) {
    public static UserResponse from(User user, String accessToken) {
        return new UserResponse(
                user.getEmail(),
                user.getRegisterDate(),
                accessToken
        );
    }
}
