package com.elkattanman.reddit.controller;

import com.elkattanman.reddit.api.v2.model.PostDto;
import com.elkattanman.reddit.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elkattanman.reddit.util.Constants.API_LINK;

@RestController
@RequestMapping(API_LINK+"posts")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto){
        return postService.save(postDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @GetMapping("by-user/{username}")
    public List<PostDto> getPostsByUsername(@PathVariable String username){
        return postService.getPostsByUsername(username);
    }
}
