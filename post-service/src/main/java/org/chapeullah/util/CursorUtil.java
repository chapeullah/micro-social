package org.chapeullah.util;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

public class CursorUtil {

    private CursorUtil() {}

    public record Cursor(Instant createdAt, Long postId) {};

    public static String encode(Instant createdAt, Long postId) {
        String raw = createdAt.toEpochMilli() + ":" + postId;
        return Base64
                .getUrlEncoder()
                .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    public static Optional<Cursor> decode(String cursor) {
        if (cursor == null || cursor.isBlank()) {
            return Optional.empty();
        }
        String raw = new String(Base64.getUrlDecoder().decode(cursor), StandardCharsets.UTF_8);
        String[] parts = raw.split(":");
        Instant createdAt = Instant.ofEpochMilli(Long.parseLong(parts[0]));
        Long postId = Long.parseLong(parts[1]);
        return Optional.of(new Cursor(createdAt, postId));
    }

}
