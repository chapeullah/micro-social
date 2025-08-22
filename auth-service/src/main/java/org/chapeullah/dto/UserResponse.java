package org.chapeullah.dto;

import org.chapeullah.entity.User;

import java.time.LocalDateTime;

public record UserResponse(Integer id,  String email, LocalDateTime registerDate, String jwtToken) {
    public static UserResponse from(User user, String jwtToken) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRegisterDate(),
                jwtToken
        );
    }
}
