package com.elkattanman.reddit.services;

import com.elkattanman.reddit.api.v2.model.VoteDto;


public interface VoteService {
    void vote(VoteDto voteDto);
}
