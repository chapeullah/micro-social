package org.chapeullah.service;

import org.chapeullah.exception.DuplicateUserException;
import org.chapeullah.infrastructure.kafka.EventsProducer;
import org.chapeullah.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtService jwtService;
    @Mock EventsProducer eventsProducer;

    @InjectMocks UserService userService;

    @Test
    void register_duplicateEmail_throwsException() {
        when(userRepository.existsByEmail("some@email.com")).thenReturn(true);
        assertThrows(DuplicateUserException.class, () -> userService.register("some@email.com", "qwerty123456"));
        verifyNoInteractions(passwordEncoder, jwtService, eventsProducer);
    }

}
