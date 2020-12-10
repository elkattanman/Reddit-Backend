package com.elkattanman.reddit.api.v2.model;


import com.elkattanman.reddit.domain.enums.VoteType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto implements Serializable {
    private VoteType voteType;
    private Long postId;
}
