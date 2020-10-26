package com.elkattanman.reddit.services.impl;

import com.elkattanman.reddit.api.v2.mapper.CommentMapper;
import com.elkattanman.reddit.api.v2.model.CommentDto;
import com.elkattanman.reddit.domain.Comment;
import com.elkattanman.reddit.domain.NotificationEmail;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.User;
import com.elkattanman.reddit.mail.MailContentBuilder;
import com.elkattanman.reddit.mail.MailService;
import com.elkattanman.reddit.repository.CommentRepository;
import com.elkattanman.reddit.repository.PostRepository;
import com.elkattanman.reddit.repository.UserRepository;
import com.elkattanman.reddit.security.jwt.AuthService;
import com.elkattanman.reddit.services.CommentService;
import com.elkattanman.reddit.services.PostService;
import com.elkattanman.reddit.services.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepository userRepository;

    @Override
    public CommentDto save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        Comment save = commentRepository.save(comment);

        String POST_URL = "posts/" + post.getPostId().toString();

        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
        return commentMapper.mapToDto(save);
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    @Override
    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    @Override
    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
