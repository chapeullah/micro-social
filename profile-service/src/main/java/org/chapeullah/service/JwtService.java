package org.chapeullah.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.chapeullah.exception.InvalidJwtTokenException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final String JWT_SECRET = System.getenv("JWT_SECRET");

    private final SecretKey secretKey;

    public JwtService() {
        byte[] jwtSecretBytes = Base64.getDecoder().decode(JWT_SECRET);
        this.secretKey = Keys.hmacShaKeyFor(jwtSecretBytes);
    }

    public Integer validateAndExtractUserId(String jwtToken) {
        try {
            Claims claims = extractClaims(jwtToken);
            if (claims.getExpiration().before(new Date())) {
                throw new InvalidJwtTokenException("token expired");
            }
            return Integer.valueOf(claims.getSubject());
        }
        catch (Exception exception) {
            throw new InvalidJwtTokenException("invalid jwt token");
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
