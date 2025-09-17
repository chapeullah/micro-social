package org.chapeullah.controller;

import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.JwtService;
import org.chapeullah.service.SocialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socials")
public final class FollowController {

    private final JwtService jwtService;
    private final SocialService socialService;

    public FollowController(
            JwtService jwtService,
            SocialService socialService
    ) {
        this.jwtService = jwtService;
        this.socialService = socialService;
    }

    @PostMapping("/follow/{targetId}")
    public void followTarget(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer targetId
    ) {
        socialService.followTarget(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                targetId
        );
    }

    @DeleteMapping("/unfollow/{targetId}")
    public void unfollowTarget(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer targetId
    ) {
        socialService.unfollowTarget(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                targetId
        );
    }

    public static String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
