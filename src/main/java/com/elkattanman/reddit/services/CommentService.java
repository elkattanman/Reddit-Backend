package com.elkattanman.reddit.services;

import com.elkattanman.reddit.api.v2.model.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto save(CommentDto commentDto);
    List<CommentDto> getAllCommentsForPost(Long postId);
    List<CommentDto> getAllCommentsForUser(String userName);
}
