package org.chapeullah.dto;

import org.chapeullah.entity.Post;

import java.time.Instant;

public record PostResponse(
        Long postId,
        Integer authorId,
        String content,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getAuthorId(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }

}
