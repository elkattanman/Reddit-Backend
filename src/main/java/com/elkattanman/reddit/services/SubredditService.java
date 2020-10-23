package com.elkattanman.reddit.services;

import com.elkattanman.reddit.api.v2.model.SubredditDto;

import java.util.List;

public interface SubredditService {

    List<SubredditDto> getAll();
    SubredditDto save(SubredditDto subredditDto);
    SubredditDto getSubreddit(Long id);
}
