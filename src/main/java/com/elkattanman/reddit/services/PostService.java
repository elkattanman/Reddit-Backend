package com.elkattanman.reddit.services;

import com.elkattanman.reddit.api.v2.model.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();
    PostDto save(PostDto postDto);
    PostDto getPost(Long id);
    List<PostDto> getPostsBySubreddit(Long subredditId);
    List<PostDto> getPostsByUsername(String username);

}
