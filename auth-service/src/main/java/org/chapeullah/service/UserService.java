package org.chapeullah.service;

import org.chapeullah.dto.UserResponse;
import org.chapeullah.entity.User;
import org.chapeullah.exception.DuplicateUserException;
import org.chapeullah.exception.InvalidCredentialsException;
import org.chapeullah.exception.InvalidJwtTokenException;
import org.chapeullah.infrastructure.kafka.EventsProducer;
import org.chapeullah.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EventsProducer eventsProducer;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EventsProducer eventsProducer
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.eventsProducer = eventsProducer;
    }

    @Transactional
    public UserResponse register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("user is already exists");
        }
        if (password.length() < 12) {
            throw new InvalidCredentialsException("password must be at least 12 characters long");
        }
        String passwordHash = passwordEncoder.encode(password);
        User user = userRepository.save(new User(email, passwordHash));
        eventsProducer.userRegistered(user.getId());
        return UserResponse.from(user, jwtService.generateJwtToken(user.getId()));
    }

    @Transactional
    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        return UserResponse.from(user, jwtService.generateJwtToken(user.getId()));
    }

    @Transactional
    public void changePassword(
            String jwtToken,
            String oldPassword,
            String newPassword
    ) {
        if (oldPassword.length() < 12 || newPassword.length() < 12) {
            throw new InvalidCredentialsException("password must be at least 12 characters long");
        }
        User user = userRepository
                .findById(jwtService.validateAndExtractUserId(jwtToken))
                .orElseThrow(() -> new InvalidJwtTokenException("invalid JWT token"));
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        if (passwordEncoder.matches(newPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException(
                    "new password must be different from the current one"
            );
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}