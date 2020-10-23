package com.elkattanman.reddit.services.impl;

import com.elkattanman.reddit.api.v2.mapper.SubredditMapper;
import com.elkattanman.reddit.api.v2.model.SubredditDto;
import com.elkattanman.reddit.domain.Subreddit;
import com.elkattanman.reddit.repository.SubredditRepository;
import com.elkattanman.reddit.services.ResourceNotFoundException;
import com.elkattanman.reddit.services.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SubredditServiceImpl implements SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
//    private final AuthService authService;

    @Override
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Override
    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        return subredditRepository.findById(id)
                .map(subredditMapper::mapSubredditToDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

}
