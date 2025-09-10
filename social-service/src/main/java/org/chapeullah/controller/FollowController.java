package org.chapeullah.controller;

import org.chapeullah.exception.InvalidAccessTokenException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socials")
public class FollowController {

    @PostMapping("/follow/{targetId}")
    public void followTarget(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer targetId
    ) {

        // TODO
    }

    public static String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
