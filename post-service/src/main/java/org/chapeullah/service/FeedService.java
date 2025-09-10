package org.chapeullah.service;

import org.chapeullah.dto.FeedResponse;
import org.chapeullah.dto.PostResponse;
import org.chapeullah.entity.Post;
import org.chapeullah.repository.PostRepository;
import org.chapeullah.util.CursorUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    private final PostRepository postRepository;

    public FeedService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public FeedResponse getFeed(Integer limit, String cursor) {
        int size = (limit == null) ? 15 : limit;
        int take = size + 1;
        PageRequest pageRequest = PageRequest.of(0, take);

        List<Post> posts;
        Optional<CursorUtil.Cursor> cursorOpt = CursorUtil.decode(cursor);
        if (cursorOpt.isEmpty()) {
            posts = postRepository.findFirstPage(pageRequest);
        }
        else {
            posts = postRepository.findNext(
                    cursorOpt.get().createdAt(),
                    cursorOpt.get().postId(),
                    pageRequest
            );
        }
        boolean hasMore = posts.size() > size;
        String nextCursor = null;
        if (hasMore) {
            posts = posts.subList(0, size);
        }
        List<PostResponse> postsR = posts.stream()
                .map(PostResponse::from)
                .toList();
        if (!postsR.isEmpty()) {
            PostResponse last = postsR.get(postsR.size() - 1);
            nextCursor = (hasMore) ? CursorUtil.encode(last.createdAt(), last.postId()) : null;
        }
        return new FeedResponse(postsR, nextCursor, hasMore);
    }

}
