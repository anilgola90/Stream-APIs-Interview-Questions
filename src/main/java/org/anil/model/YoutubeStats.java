package org.anil.model;

import java.time.LocalDateTime;
import java.util.List;

public record YoutubeStats(
    String videoId,
    String title,
    String channel,
    String category,
    LocalDateTime publishedTime,
    Long views,
    Long likes,
    Long dislikes,
    Long commentCount
) {
}
