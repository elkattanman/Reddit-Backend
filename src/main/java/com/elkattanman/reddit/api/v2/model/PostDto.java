package com.elkattanman.reddit.api.v2.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long postId;
    private String postName;
    private String url;
    private String description;
    private String subredditName;
    private String userName;
}
