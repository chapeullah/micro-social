package org.chapeullah.dto;

import org.chapeullah.entity.User;

import java.time.LocalDateTime;

public record UserResponse(Integer id, String username, String email, LocalDateTime registerDate) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRegisterDate()
        );
    }
}
