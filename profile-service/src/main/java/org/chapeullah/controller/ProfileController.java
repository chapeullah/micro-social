package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.*;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.JwtService;
import org.chapeullah.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public final class ProfileController {

    private final ProfileService profileService;
    private final JwtService jwtService;

    public ProfileController(ProfileService profileService, JwtService jwtService) {
        this.profileService = profileService;
        this.jwtService = jwtService;
    }

    @GetMapping("/get")
    public ProfileResponse getProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(name = "userId") Integer userId
    ) {
        jwtService.validate(parseAuthHeader(authHeader));
        return profileService.getProfileResponseById(userId);
    }

    @PatchMapping("/update/username")
    public ProfileResponse updateUsername(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateUsernameRequest request
    ) {
        return profileService.updateUsername(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                request.username()
        );
    }

    @PatchMapping("/update/birthday")
    public ProfileResponse updateBirthday(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateBirthdayRequest request
    ) {
        return profileService.updateBirthday(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                request.birthday()
        );
    }

    @PatchMapping("/update/location/country")
    public ProfileResponse updateLocationCountry(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateLocationCountryRequest request
    ) {
        return profileService.updateLocationCounty(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                request.country()
        );
    }

    @PatchMapping("/update/location/city")
    public ProfileResponse updateLocationCity(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateLocationCityRequest request
    ) {
        return profileService.updateLocationCity(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                request.city()
        );
    }

    private String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
