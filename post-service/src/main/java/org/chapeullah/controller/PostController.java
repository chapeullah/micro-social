package org.chapeullah.controller;

import jakarta.validation.Valid;
import org.chapeullah.dto.PostRequest;
import org.chapeullah.dto.PostResponse;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public PostResponse createPost(
            @RequestHeader("Authorization") String accessToken,
            @Valid @RequestBody PostRequest request
    ) {
        return postService.createPost(
                parseAuthHeader(accessToken),
                request.content()
        );
    }

    @DeleteMapping("/delete/{postId}")
    public PostResponse deletePost(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Long postId
    ) {
        return postService.deletePost(accessToken, postId);
    }

    @GetMapping("/get/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PatchMapping("/update/{postId}")
    public PostResponse updatePost(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Long postId,
            @RequestBody PostRequest request
    ) {
        return postService.updatePost(
                accessToken,
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
