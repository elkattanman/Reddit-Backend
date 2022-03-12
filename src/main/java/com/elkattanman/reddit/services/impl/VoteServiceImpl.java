package com.elkattanman.reddit.services.impl;

import com.elkattanman.reddit.api.v2.model.VoteDto;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.Vote;
import com.elkattanman.reddit.exception.SpringRedditException;
import com.elkattanman.reddit.repository.PostRepository;
import com.elkattanman.reddit.repository.VoteRepository;
import com.elkattanman.reddit.security.jwt.AuthService;
import com.elkattanman.reddit.services.ResourceNotFoundException;
import com.elkattanman.reddit.services.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.elkattanman.reddit.domain.enums.VoteType.UPVOTE;

@Service
@AllArgsConstructor
@Slf4j
public class VoteServiceImpl implements VoteService {

    public final PostRepository postRepository;
    public final VoteRepository voteRepository;
    public final AuthService authService;

    @Override
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            if(voteByPostAndUser.isPresent())
                post.setVoteCount(post.getVoteCount() + 2);
            else
                post.setVoteCount(post.getVoteCount() + 1);
        } else {
            if(voteByPostAndUser.isPresent())
                post.setVoteCount(post.getVoteCount() - 2);
            else
                post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}
