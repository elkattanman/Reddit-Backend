package com.elkattanman.reddit.api.v2.model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {
    private Long postId;
    private String postName;
    private String url;
    private String description;
    private String subredditName;
    private String userName;
    private int  voteCount;
    private Integer commentCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;
}
