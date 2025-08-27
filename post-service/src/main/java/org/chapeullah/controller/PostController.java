package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.CreatePostRequest;
import org.chapeullah.dto.PostResponse;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public PostResponse createPost(
            @RequestHeader("Authorization") String accessToken,
            @Valid @RequestBody CreatePostRequest request
    ) {
        return postService.createPost(
                parseAuthHeader(accessToken),
                request.content()
        );
    }

    @DeleteMapping("/delete")

    @GetMapping("/get/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    public static String parseAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAccessTokenException("missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

}
