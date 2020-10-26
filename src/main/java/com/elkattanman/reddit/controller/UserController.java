package com.elkattanman.reddit.controller;

import com.elkattanman.reddit.api.v2.model.CommentDto;
import com.elkattanman.reddit.api.v2.model.PostDto;
import com.elkattanman.reddit.services.CommentService;
import com.elkattanman.reddit.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elkattanman.reddit.util.Constants.API_LINK;

@RestController
@RequestMapping(API_LINK+"users")
@AllArgsConstructor
public class UserController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("{username}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostsByUsername(@PathVariable String username){
        return postService.getPostsByUsername(username);
    }

    @GetMapping("{username}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllCommentsForUser(@PathVariable String username){
        return commentService.getAllCommentsForUser(username);
    }

}
