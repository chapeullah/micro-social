package org.chapeullah.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
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

    public String generateAccessToken(Integer userId) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date())
                .expiration(new Date(now + ACCESS_MS))
                .signWith(secretKey)
                .compact();
    }

    public Integer validateAndExtractUserId(String jwtToken) {
        try {
            Claims claims = extractClaims(jwtToken);
            if (claims.getExpiration().before(new Date())) {
                throw new InvalidAccessTokenException("access token expired");
            }
            return Integer.valueOf(claims.getSubject());
        }
        catch (Exception exception) {
            throw new InvalidAccessTokenException("invalid access token");
        }
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

}
