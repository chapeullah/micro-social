package org.chapeullah.service;

import org.chapeullah.dto.PostResponse;
import org.chapeullah.entity.Post;
import org.chapeullah.exception.AccessDeniedException;
import org.chapeullah.exception.PostNotFoundException;
import org.chapeullah.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(
            PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostResponse createPost(Integer userId, String content) {
        Post post = new Post(userId, content);
        postRepository.save(post);
        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse updatePost(Integer userId, Long postId, String content) {
        Post post = getPostById(postId);
        if (post.isDeleted()) {
            throw new PostNotFoundException("post not found");
        }
        if (!post.getAuthorId().equals(userId)) {
            throw new AccessDeniedException("access denied");
        }
        post.setContent(content);
        post.setUpdatedAt(Instant.now());
        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse deletePost(Integer userId, Long postId) {
        Post post = getPostById(postId);
        if (!post.isAuthor(userId)) {
            throw new AccessDeniedException("access denied");
        }
        if (post.isDeleted()) {
            throw new PostNotFoundException("post not found");
        }
        post.setDeletedAt(Instant.now());
        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = getPostById(postId);
        if (post.isDeleted()) {
            throw new PostNotFoundException("post not found");
        }
        return PostResponse.from(post);
    }

    private Post getPostById(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException("post not found"));
    }

}
