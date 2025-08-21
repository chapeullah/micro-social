package org.chapeullah.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final String JWT_SECRET = System.getenv("JWT_SECRET");
    private static final long ACCESS_MS = Duration.ofDays(7).toMillis();

    private final SecretKey secretKey;

    public JwtService() {
        byte[] jwtSecretBytes = Base64.getDecoder().decode(JWT_SECRET);
        this.secretKey = Keys.hmacShaKeyFor(jwtSecretBytes);
    }

    public String generate(Integer userId) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(now))
                .expiration(new Date(now + ACCESS_MS))
                .signWith(secretKey)
                .compact();
    }

    public Integer validateAndExtractUserId(String token) {
        try {
            Claims claims = extractClaims(token);
            if (claims.getExpiration().before(new Date())) {
                throw new IllegalStateException("Token expired");
            }
            return Integer.valueOf(claims.getSubject());
        }
        catch (Exception exception) {
            throw new IllegalStateException("Invalid JWT token", exception);
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



}
