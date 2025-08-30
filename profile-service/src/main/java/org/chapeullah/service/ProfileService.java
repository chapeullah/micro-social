package org.chapeullah.service;

import org.chapeullah.dto.ProfileResponse;
import org.chapeullah.entity.Profile;
import org.chapeullah.exception.MissingLocationCountryException;
import org.chapeullah.exception.UserNotFoundException;
import org.chapeullah.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProfileService {

    ProfileRepository profileRepository;

    public ProfileService(
            ProfileRepository profileRepository
    ) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void create(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public ProfileResponse updateUsername(Integer userId, String username) {
        Profile profile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        profile.setUsername(username);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateBirthday(
            Integer userId,
            LocalDate birthday
    ) {
        Profile profile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        profile.setBirthday(birthday);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateLocationCounty(Integer userId, String locationCountry) {
        Profile profile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        profile.setLocationCountry(locationCountry);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateLocationCity(Integer userId, String locationCity) {
        Profile profile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        if (profile.getLocationCountry() == null) {
            throw new MissingLocationCountryException("location country must be set before assigning a city");
        }
        profile.setLocationCity(locationCity);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse getProfileResponseById(Integer userId) {
        return ProfileResponse.from(
                profileRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("invalid user id"))
        );
    }

}
