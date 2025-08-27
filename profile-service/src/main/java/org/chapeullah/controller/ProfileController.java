package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.*;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get")
    public ProfileResponse getProfile(
            @RequestHeader("Authorization") String authHeader
    ) {
        return profileService.getProfileResponseByJwt(parseAuthHeader(authHeader));
    }

    @PatchMapping("/update/username")
    public ProfileResponse updateUsername(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateUsernameRequest request
    ) {
        return profileService.updateUsername(parseAuthHeader(authHeader), request.username());
    }

    @PatchMapping("/update/birthday")
    public ProfileResponse updateBirthday(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateBirthdayRequest request
    ) {
        return profileService.updateBirthday(parseAuthHeader(authHeader), request.birthday());
    }

    @PatchMapping("/update/location/country")
    public ProfileResponse updateLocationCountry(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateLocationCountryRequest request
    ) {
        return profileService.updateLocationCounty(parseAuthHeader(authHeader), request.country());
    }

    @PatchMapping("/update/location/city")
    public ProfileResponse updateLocationCity(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateLocationCityRequest request
    ) {
        return profileService.updateLocationCity(parseAuthHeader(authHeader), request.city());
    }

    private String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
