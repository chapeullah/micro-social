package org.chapeullah.controller;

import org.chapeullah.dto.FeedResponse;
import org.chapeullah.service.FeedService;
import org.chapeullah.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;
    private final JwtService jwtService;

    public FeedController(
            FeedService feedService,
            JwtService jwtService
    ) {
        this.feedService = feedService;
        this.jwtService = jwtService;
    }

    @GetMapping("/get")
    public FeedResponse getFeed(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(required = false, defaultValue = "15") Integer limit,
            @RequestParam(required = false) String cursor
    ) {
        jwtService.validateAndExtractUserId(PostController.parseAuthHeader(authHeader));
        return feedService.getFeed(limit, cursor);
    }

}
