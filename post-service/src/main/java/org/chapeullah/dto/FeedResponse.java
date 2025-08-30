package org.chapeullah.dto;

import java.util.List;

public record FeedResponse(
        List<PostResponse> posts,
        String cursor,
        boolean hasMore
) {}
