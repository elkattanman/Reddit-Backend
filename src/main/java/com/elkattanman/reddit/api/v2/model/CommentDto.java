package com.elkattanman.reddit.api.v2.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
}
