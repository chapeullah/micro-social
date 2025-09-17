package org.chapeullah.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    Long followId;

    @Column(nullable = false)
    @Getter @Setter
    Integer followerId;

    @Column(nullable = false)
    @Getter @Setter
    Integer targetId;

    @Column(nullable = false)
    @Getter
    Instant createdAt = Instant.now();

    protected Follow() {}

    public Follow(Integer followerId, Integer targetId) {
        this.followerId = followerId;
        this.targetId = targetId;
    }

}
