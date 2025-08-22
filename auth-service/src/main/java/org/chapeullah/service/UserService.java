package org.chapeullah.service;

import org.chapeullah.dto.UserResponse;
import org.chapeullah.entity.User;
import org.chapeullah.exception.DuplicateUserException;
import org.chapeullah.exception.InvalidCredentialsException;
import org.chapeullah.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("user is already exists");
        }
        if (password.length() < 12) {
            throw new InvalidCredentialsException("password must be at least 12 characters long");
        }
        String passwordHash = passwordEncoder.encode(password);
        User user = userRepository.save(new User(email, passwordHash));
        return UserResponse.from(user, jwtService.generate(user.getId()));
    }

    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        return UserResponse.from(user, jwtService.generate(user.getId()));
    }
}