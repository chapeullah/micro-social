package org.chapeullah;

import java.time.Instant;

public record UserRegistered(int userId, Instant registeredDate) {}