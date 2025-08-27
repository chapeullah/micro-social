package org.chapeullah.service;

import org.chapeullah.dto.ProfileResponse;
import org.chapeullah.entity.Profile;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.exception.MissingLocationCountryException;
import org.chapeullah.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProfileService {

    ProfileRepository profileRepository;
    JwtService jwtService;

    public ProfileService(
            ProfileRepository profileRepository,
            JwtService jwtService
    ) {
        this.profileRepository = profileRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public void create(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public ProfileResponse updateUsername(String jwtToken, String username) {
        Profile profile = getProfileByJwt(jwtToken);
        profile.setUsername(username);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateBirthday(
            String jwtToken,
            LocalDate birthday
    ) {
        Profile profile = getProfileByJwt(jwtToken);
        profile.setBirthday(birthday);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateLocationCounty(String jwtToken, String locationCountry) {
        Profile profile = getProfileByJwt(jwtToken);
        profile.setLocationCountry(locationCountry);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateLocationCity(String jwtToken, String locationCity) {
        Profile profile = getProfileByJwt(jwtToken);
        if (profile.getLocationCountry() == null) {
            throw new MissingLocationCountryException("location country must be set before assigning a city");
        }
        profile.setLocationCity(locationCity);
        profileRepository.save(profile);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse getProfileResponseByJwt(String jwtToken) {
        return ProfileResponse.from(getProfileByJwt(jwtToken));
    }

    private Profile getProfileByJwt(String jwtToken) {
        return profileRepository
                .findById(jwtService.validateAndExtractUserId(jwtToken))
                .orElseThrow(() -> new InvalidAccessTokenException("invalid jwt token"));
    }

}
