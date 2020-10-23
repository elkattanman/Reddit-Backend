package com.elkattanman.reddit.controller;


import com.elkattanman.reddit.api.v2.model.PostDto;
import com.elkattanman.reddit.api.v2.model.SubredditDto;
import com.elkattanman.reddit.services.PostService;
import com.elkattanman.reddit.services.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elkattanman.reddit.util.Constants.API_LINK;

@RestController
@RequestMapping(API_LINK+"subreddits")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubredditDto createSubreddit(@RequestBody SubredditDto subredditDto){
        return subredditService.save(subredditDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubredditDto> getAllSubreddit(){
        return subredditService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SubredditDto getById(@PathVariable Long id){
        return subredditService.getSubreddit(id);
    }


    @GetMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostsBySubreddit(@PathVariable Long id){
        return postService.getPostsBySubreddit(id);
    }

}
