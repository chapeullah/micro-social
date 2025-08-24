package org.chapeullah.service;

import org.chapeullah.entity.Profile;
import org.chapeullah.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void create(Profile profile) {
        profileRepository.save(profile);
    }



}
