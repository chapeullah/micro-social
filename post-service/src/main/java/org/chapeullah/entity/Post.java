package org.chapeullah.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter @Setter
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Integer authorId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column
    private Instant updatedAt;

    @Column
    private Instant deletedAt;

    @Column(nullable = false)
    private Integer likes = 0;

    protected Post() {}

    public Post(Integer authorId, String content) {
        this.authorId = authorId;
        this.content = content;
    }

    public boolean isDeleted() {
        return deletedAt == null;
    }

}
