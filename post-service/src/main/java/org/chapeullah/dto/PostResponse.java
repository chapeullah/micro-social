package org.chapeullah.dto;

import org.chapeullah.entity.Post;

import java.time.Instant;

public record PostResponse(
        Long postId,
        String content,
        Instant createdAt,
        Instant updatedAt
) {

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

}
