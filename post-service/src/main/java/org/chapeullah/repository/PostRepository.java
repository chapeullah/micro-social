package org.chapeullah.repository;

import org.chapeullah.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        SELECT post FROM Post post
        WHERE post.deletedAt IS NULL
        ORDER BY post.createdAt DESC, post.postId DESC
        """)
    List<Post> findFirstPage(Pageable pageable);

    @Query("""
        SELECT post FROM Post post
        WHERE post.deletedAt IS NULL
          AND (post.createdAt < :ts OR (post.createdAt = :ts AND post.postId < :postId))
        ORDER BY post.createdAt DESC, post.postId DESC
        """)
    List<Post> findNext(
            @Param("ts") Instant ts,
            @Param("postId") Long postId,
            Pageable pageable
    );
}

