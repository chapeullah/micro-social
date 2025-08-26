package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.ProfileResponse;
import org.chapeullah.dto.UpdateBirthdayRequest;
import org.chapeullah.dto.UpdateFieldRequest;
import org.chapeullah.exception.InvalidJwtTokenException;
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
    public ProfileResponse getProfile(@RequestHeader("Authorization") String authHeader) {
        return profileService.getProfileResponseByJwt(parseAuthHeader(authHeader));
    }

    @PostMapping("/update/username")
    public ProfileResponse updateUsername(@RequestHeader("Authorization") String authHeader,
                                          @Valid @RequestBody UpdateFieldRequest request) {
        return profileService.updateUsername(parseAuthHeader(authHeader), request.field());
    }

    @PostMapping("/update/birthday")
    public ProfileResponse updateBirthday(@RequestHeader("Authorization") String authHeader,
                                          @Valid @RequestBody UpdateBirthdayRequest request) {
        return profileService.updateBirthday(parseAuthHeader(authHeader), request.birthday());
    }

    @PostMapping("/update/location/country")
    public ProfileResponse updateLocationCountry(@RequestHeader("Authorization") String authHeader,
                                                 @Valid @RequestBody UpdateFieldRequest request) {
        return profileService.updateLocationCounty(parseAuthHeader(authHeader), request.field());
    }

    @PostMapping("/update/location/city")
    public ProfileResponse updateLocationCity(@RequestHeader("Authorization") String authHeader,
                                              @Valid @RequestBody UpdateFieldRequest request) {
        return profileService.updateLocationCity(parseAuthHeader(authHeader), request.field());
    }

    private String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidJwtTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
