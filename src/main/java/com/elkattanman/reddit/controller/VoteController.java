package com.elkattanman.reddit.controller;


import com.elkattanman.reddit.api.v2.model.VoteDto;
import com.elkattanman.reddit.services.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.elkattanman.reddit.util.Constants.API_LINK;

@RestController
@RequestMapping(API_LINK+"votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void vote(@RequestBody VoteDto voteDto){
        voteService.vote(voteDto);
    }

}
