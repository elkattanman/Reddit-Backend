package com.elkattanman.reddit.services.impl;

import com.elkattanman.reddit.api.v2.mapper.PostMapper;
import com.elkattanman.reddit.api.v2.model.PostDto;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.Subreddit;
import com.elkattanman.reddit.domain.User;
import com.elkattanman.reddit.repository.PostRepository;
import com.elkattanman.reddit.repository.SubredditRepository;
import com.elkattanman.reddit.repository.UserRepository;
import com.elkattanman.reddit.security.jwt.AuthService;
import com.elkattanman.reddit.services.PostService;
import com.elkattanman.reddit.services.ResourceNotFoundException;
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
public class PostServiceImpl implements PostService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Override
    public PostDto save(PostDto postDto) {
        Subreddit subreddit = subredditRepository.findByName(postDto.getSubredditName())
                .orElseThrow(() -> new ResourceNotFoundException(postDto.getSubredditName()));
        Post save = postRepository.save(postMapper.map(postDto, subreddit, authService.getCurrentUser()));
        return postMapper.mapToDto(save);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new ResourceNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
