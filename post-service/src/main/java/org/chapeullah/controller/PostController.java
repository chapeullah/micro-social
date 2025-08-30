package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.PostRequest;
import org.chapeullah.dto.PostResponse;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.JwtService;
import org.chapeullah.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    public PostController(PostService postService, JwtService jwtService) {
        this.postService = postService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public PostResponse createPost(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody PostRequest request
    ) {
        return postService.createPost(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                request.content()
        );
    }

    @DeleteMapping("/delete/{postId}")
    public PostResponse deletePost(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long postId
    ) {
        return postService.deletePost(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                postId
        );
    }

    @GetMapping("/get/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PatchMapping("/update/{postId}")
    public PostResponse updatePost(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long postId,
            @RequestBody PostRequest request
    ) {
        return postService.updatePost(
                jwtService.validateAndExtractUserId(parseAuthHeader(authHeader)),
                postId,
                request.content()
        );
    }

    public static String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
