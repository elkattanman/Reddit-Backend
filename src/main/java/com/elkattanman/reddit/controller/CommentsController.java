package com.elkattanman.reddit.controller;


import com.elkattanman.reddit.api.v2.model.CommentDto;
import com.elkattanman.reddit.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.elkattanman.reddit.util.Constants.API_LINK;

@RestController
@RequestMapping(API_LINK+"comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@RequestBody CommentDto commentsDto){
        return commentService.save(commentsDto);
    }

}
