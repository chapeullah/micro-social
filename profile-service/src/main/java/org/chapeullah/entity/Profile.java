package org.chapeullah.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.chapeullah.UserRegistered;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Setter
@Getter
public class Profile {

    @Id
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, updatable = false)
    private Instant registeredAt;

    @Column
    private LocalDate birthday;

    @Column
    private String locationCountry;

    @Column
    private String locationCity;

    protected Profile() {}

    public Profile(UserRegistered userRegistered) {
        this.userId = userRegistered.userId();
        this.registeredAt = userRegistered.registeredDate();
        this.username = generateUsername(this.userId);
    }

    private final String generateUsername(Integer userId) {
        return String.format("User%05d", userId);
    }

}
